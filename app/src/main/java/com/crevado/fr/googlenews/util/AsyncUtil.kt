package com.crevado.fr.googlenews.util

import android.annotation.SuppressLint
import android.os.AsyncTask

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

