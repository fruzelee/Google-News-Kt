package com.crevado.fr.googlenews.util

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.crevado.fr.googlenews.AppController
/**
 * Created by FazLe Rabbi on 13,June,2020
 * fazle.rabbi.cse@gmail.com
 * <p>
 * Copyright (c) 2020 FazLe Rabbi
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

