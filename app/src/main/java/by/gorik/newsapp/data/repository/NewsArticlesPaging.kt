package by.gorik.newsapp.data.repository

import by.gorik.newsapp.data.api.NetworkService
import by.gorik.newsapp.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsArticlesPaging(val net: NetworkService) {

    //    ::: GET by COUNTRY
    fun getArticlesbyCountry(country: String): Flow<List<ApiArticle>> {
        return flow {
            emit(net.getTopCountry(country))
        }.map { it.articles }
    }
//    ::: GET by LANGUAGE


    fun getArticlesbyLanguage(language: String): Flow<List<ApiArticle>> {
        return flow {
            emit(net.getTopLanguage(language))
        }.map { it.articles }
    }

//    ::: GET by QUERY

    fun getArticlesbyQuery(query: String): Flow<List<ApiArticle>> {
        return flow {
            emit(net.getTopQuery(query))
        }.map { it.articles }
    }

//    ::: GET by SOurces

    fun getArticlesbySources(sources: String): Flow<List<ApiArticle>> {
        return flow {
            emit(net.getTopSources(sources))
        }.map { it.articles }
    }
}