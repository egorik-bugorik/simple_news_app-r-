package by.gorik.newsapp.di.module

import android.content.Context
import androidx.room.Room
import by.gorik.newsapp.data.api.ApiInterceptor
import by.gorik.newsapp.data.api.NetworkService
import by.gorik.newsapp.data.local.database.DbService
import by.gorik.newsapp.data.local.database.DbServiceImpl
import by.gorik.newsapp.data.local.database.NewsDatabase
import by.gorik.newsapp.di.BaseUrl
import by.gorik.newsapp.di.DatabaseName
import by.gorik.newsapp.di.NetworkApiKey
import by.gorik.newsapp.utils.Constants
import by.gorik.newsapp.utils.Constants.API_KEY
import by.gorik.newsapp.utils.DefaultDispatcherProvider
import by.gorik.newsapp.utils.DefaultResourceProvider
import by.gorik.newsapp.utils.DispatcherProvider
import by.gorik.newsapp.utils.ResourceProvider
import by.gorik.newsapp.utils.logger.Logger
import by.gorik.newsapp.utils.logger.LoggerImpl
import by.gorik.newsapp.utils.network.NetworkHelper
import by.gorik.newsapp.utils.network.NetworkHelperDefault
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL

    @NetworkApiKey
    @Provides
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkApiKey apiKey: String): ApiInterceptor =
        ApiInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiInterceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(apiKeyInterceptor).build()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)

    }

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperDefault(context)
    }

    @Provides
    @Singleton
    fun provideStringHelper(@ApplicationContext context: Context): ResourceProvider {
        return DefaultResourceProvider(context)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = "news-database"

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(newsDatabase: NewsDatabase): DbService {
        return DbServiceImpl(newsDatabase)
    }

//    @Provides
//    @Singleton
//    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
//        return WorkManager.getInstance(context)
//    }

}