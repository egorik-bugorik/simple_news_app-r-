package by.gorik.newsapp.data.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(val apiKey:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origRequest = chain.request()
        val builder  = origRequest.newBuilder().addHeader("X-Api-Key",apiKey)
        val request = builder.build()
        return chain.proceed(request)
    }


}