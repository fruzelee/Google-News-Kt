package com.crevado.fr.googlenews.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crevado.fr.googlenews.model.NewsData
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