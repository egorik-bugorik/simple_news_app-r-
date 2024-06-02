package by.gorik.newsapp.data.repository

import by.gorik.newsapp.data.model.Country
import by.gorik.newsapp.data.model.Language
import by.gorik.newsapp.utils.COUNTRIES
import by.gorik.newsapp.utils.LANGUAGES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepo @Inject constructor () {
    fun getCountry():Flow<List<Country>>{
        return flow {
            emit(COUNTRIES)
        }
    }
}