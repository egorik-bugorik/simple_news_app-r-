package by.gorik.newsapp.data.local.database

import by.gorik.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DbService {
    fun getArticles(): Flow<List<Article>>
    fun deleteInsert(articles:List<Article>)
}