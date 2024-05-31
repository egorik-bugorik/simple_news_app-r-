package by.gorik.newsapp.data.local.database

import by.gorik.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

class DbServiceImpl(val db:NewsDatabase) : DbService {
    override fun getArticles(): Flow<List<Article>> {
        return db.getDao().getArticles()
    }

    override fun deleteInsert(articles: List<Article>) {
        return db.getDao().deleteInsert(articles)
    }
}