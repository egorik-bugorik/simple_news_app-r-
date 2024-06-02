package by.gorik.newsapp.data.api

import android.util.Log
import by.gorik.newsapp.di.NetworkApiKey
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(@NetworkApiKey val apiKey:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origRequest = chain.request()
        val builder  = origRequest.newBuilder().header("X-Api-Key",apiKey)
        val request = builder.build()
        Log.i("RIOGORIO ", "query:::: :  ${request}")
        return chain.proceed(request)
    }


}