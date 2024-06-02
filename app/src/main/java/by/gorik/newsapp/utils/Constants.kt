package by.gorik.newsapp.utils

import by.gorik.newsapp.BuildConfig

object Constants{
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://newsapi.org/v2/"
        val SEARCH_NEWS = "SEARCH_NEWS"
    val MIN_SEARCH_LEN = 5
    val NEWS_PAGING = "NEWS_PAGING"
    val NEWS_ONLINE = "NEWS ONLINE"
    val SOURCES_CHOOSE = "Choose  news source"
    val LANGUAGE_CHOOSE = "Choose your language"
    const val COUNTRY_CHOOSE = "Choose country"
    const val DEFAULT_COUNTRY = "us"
    const val INITIAL_PAGE = 1
    const val PAGE_SIZE = 10
    const val APP_NAME = "NEWs by gorik"

}
