package com.crevado.fr.googlenews.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crevado.fr.googlenews.model.NewsData

@Dao
interface NewsDao {

    // insert news
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertNews(news: NewsData?):Long

    // get all news sorted by date
    @Query("SELECT * FROM `NewsData` order by `published_at` desc")
    fun getNews(): List<NewsData>

}