package com.crevado.fr.googlenews.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.crevado.fr.googlenews.AppController

@Suppress("DEPRECATION")
class Utils {
    companion object {

        fun isNetworkAvailable(): Boolean {

            val connectivityManager =
                AppController.app.getSystemService(android.content.Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val activeNetwork =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

                return when {

                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            } else {
                return connectivityManager.activeNetworkInfo != null &&
                        connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
            }
        }

        /*fun isNetworkAvailableSimple(): Boolean {
           val connectivityManager =
               AppController.app.getSystemService(Context.CONNECTIVITY_SERVICE)
           return if (connectivityManager is ConnectivityManager) {

               val networkInfo: NetworkInfo? = connectivityManager.allNetworkInfo
               networkInfo?.isConnected ?: false
           } else false
       }*/

    }

}

