package by.gorik.newsapp.data.repository

import by.gorik.newsapp.data.api.NetworkService
import by.gorik.newsapp.data.model.ApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsSourcesRepo(val net:NetworkService) {
    fun getNewsSources():Flow<List<ApiSource>>{
        return flow{
            emit(net.getSources())
        }.map { it.sources }
    }
}