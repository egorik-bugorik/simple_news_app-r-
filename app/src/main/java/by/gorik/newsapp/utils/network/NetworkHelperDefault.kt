package by.gorik.newsapp.utils.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkHelperDefault (val ctx:Context): NetworkHelper {
    override fun isNetworkActive(): Boolean {
        val cm =ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}