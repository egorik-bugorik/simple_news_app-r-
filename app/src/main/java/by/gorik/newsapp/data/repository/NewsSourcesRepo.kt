package by.gorik.newsapp.data.repository

import by.gorik.newsapp.data.api.NetworkService
import by.gorik.newsapp.data.model.ApiSource
import by.gorik.newsapp.data.model.Language
import by.gorik.newsapp.utils.LANGUAGES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsSourcesRepo(val net:NetworkService) {
    fun getNewsSourcesa():Flow<List<ApiSource>>{
        return flow{
            emit(net.getSources())
        }.map { it.sources }
    }
}