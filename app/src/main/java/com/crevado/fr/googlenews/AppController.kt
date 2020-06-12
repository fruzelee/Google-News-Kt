package com.crevado.fr.googlenews

import android.app.Application
import com.crevado.fr.googlenews.di.*

class AppController : Application() {

    lateinit var mApiComponent: ApiComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        mApiComponent = DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .apiHelper(ApiHelper())
            .dBModule(DBModule(this))
            .build()
    }

    companion object {
        lateinit var app: AppController
            private set
    }

}