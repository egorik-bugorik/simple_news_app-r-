package by.gorik.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.gorik.newsapp.data.local.dao.NewsDao
import by.gorik.newsapp.data.local.entity.Article


@Database (entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase :RoomDatabase(){


    abstract fun getDao(): NewsDao
}