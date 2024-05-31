package by.gorik.newsapp.utils.logger

import android.util.Log

class LoggerImpl : Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}