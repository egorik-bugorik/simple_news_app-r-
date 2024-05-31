package by.gorik.newsapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

interface DispatcherProvider {

    val io:CoroutineDispatcher
    val main:CoroutineDispatcher
    val default:CoroutineDispatcher



}


class DefaultDispatcherProvider():DispatcherProvider{
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

}

