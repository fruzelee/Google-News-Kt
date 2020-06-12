package com.crevado.fr.googlenews.di

import android.app.Application
import androidx.room.Room
import com.crevado.fr.googlenews.room.AppDB
import com.crevado.fr.googlenews.room.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Suppress("SpellCheckingInspection")
@Module(includes = [AppModule::class])
class DBModule(mApplication: Application) {
    private val newsDB: AppDB =
        Room.databaseBuilder(mApplication, AppDB::class.java, "GoogleNews.sqlite").build()

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): AppDB {
        return newsDB
    }

    @Singleton
    @Provides
    internal fun providesProductDao(newsDB: AppDB): NewsDao {
        return newsDB.newsDao()
    }
}