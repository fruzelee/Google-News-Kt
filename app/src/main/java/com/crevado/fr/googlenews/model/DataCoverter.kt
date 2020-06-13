package com.crevado.fr.googlenews.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
class DataConverter {

    @TypeConverter
    fun fromSource(value:NewsData.Source): String {
        val gSon = Gson()
        val type = object : TypeToken<NewsData.Source>() {}.type
        return gSon.toJson(value, type)
    }

    @TypeConverter
    fun toSource(value: String): NewsData.Source {
        val gSon = Gson()
        val type = object : TypeToken<NewsData.Source>() {}.type
        return gSon.fromJson(value, type)
    }
}