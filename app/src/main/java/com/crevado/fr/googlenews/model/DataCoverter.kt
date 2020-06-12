package com.crevado.fr.googlenews.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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