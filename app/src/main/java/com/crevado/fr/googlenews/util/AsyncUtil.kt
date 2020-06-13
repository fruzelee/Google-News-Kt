package com.crevado.fr.googlenews.util

import android.annotation.SuppressLint
import android.os.AsyncTask
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
@Suppress("unused")
abstract class AsyncUtil<T>(val daoProcessCallback: DaoProcessCallback<T>?) {

    interface DaoProcessCallback<T> {
        fun onResult(result: T)
    }

    protected abstract fun doAsync(): T

    fun start() {
        DaoProcessAsyncTask().execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class DaoProcessAsyncTask : AsyncTask<Void, Void, T>() {

        override fun doInBackground(vararg params: Void): T {
            return doAsync()
        }

        override fun onPostExecute(t: T) {
            daoProcessCallback?.onResult(t)
        }
    }
}

