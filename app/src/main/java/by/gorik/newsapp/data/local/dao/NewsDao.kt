package by.gorik.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import by.gorik.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {


    @Query("SELECT * FROM article")
    fun getArticles():Flow<List<Article>>
    @Insert
    fun insertArticles(articles:List<Article>)
    @Query("DELETE  FROM article")
    fun deleteArticles()

    @Transaction
    fun deleteInsert(articles:List<Article>){
        deleteArticles()
        insertArticles(articles)
    }
}