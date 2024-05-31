package by.gorik.newsapp.data.repository

import androidx.compose.ui.unit.Constraints
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import by.gorik.newsapp.data.api.NetworkService
import by.gorik.newsapp.data.local.database.DbServiceImpl
import by.gorik.newsapp.data.local.database.NewsDatabase
import by.gorik.newsapp.data.local.entity.Article
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class NewArticlesRepo(
    val net: NetworkService,
    val db: DbServiceImpl

) {

    fun getArticlesOnline(country: String): Flow<List<ApiArticle>> {
        return flow {
            emit(net.getTop(country))
        }.map { it.articles }
    }

    fun getArticlesOffline(country: String): Flow<List<Article>> {
        return flow {
            emit(net.getTop(country))
        }.map {
            it.articles.map {
                it.toArticle()
            }
        }.flatMapConcat { articles ->
            flow {
                emit(db.deleteInsert(articles))
            }
        }.flatMapConcat { articles ->
            db.getArticles()
        }
    }

    fun getArticlesFromDb(): Flow<List<Article>> {
        return db.getArticles()
    }

    fun getPagingArticle(): Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = {
                NewsArticlesPagingSource(net)
            }
        ).flow

    }
}

