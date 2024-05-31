package by.gorik.newsapp.utils

import android.content.Context
import by.gorik.newsapp.R

interface ResourceProvider {
    fun getStringInternetNotAvailable(): String
}

class DefaultResourceProvider(val ctx: Context) : ResourceProvider {
    override fun getStringInternetNotAvailable(): String {
        return ctx.getString(R.string.no_internet_available)
    }
}