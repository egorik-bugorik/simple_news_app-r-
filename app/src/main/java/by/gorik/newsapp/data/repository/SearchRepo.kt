package by.gorik.newsapp.data.repository

import by.gorik.newsapp.data.api.NetworkService
import by.gorik.newsapp.data.model.ApiArticle
import by.gorik.newsapp.data.model.ApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchRepo(val net:NetworkService) {
    fun getNewsSearch(query:String):Flow<List<ApiArticle>>{
        return flow{
            emit(net.getTopQuery(query))
        }.map { it.articles }
    }
}