package by.gorik.newsapp.data.api

import by.gorik.newsapp.data.model.NewsSourcesResponse
import by.gorik.newsapp.data.model.TopHeadlinesResponse
import by.gorik.newsapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTop(@Query("country") country: String = Constants.DEFAULT_COUNTRY): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopCountry(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopLanguage(@Query("language") language: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopSources(@Query("sources") sources: String): TopHeadlinesResponse


    @GET("top-headlines")
    suspend fun getTopQuery(@Query("search") search: String): TopHeadlinesResponse


    @GET("top-headlines")
    suspend fun getTopPaging(
        @Query("country") country: String = Constants.DEFAULT_COUNTRY,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        ): TopHeadlinesResponse


    @GET("top-headlines/sources")
    suspend  fun getSources(): NewsSourcesResponse
}