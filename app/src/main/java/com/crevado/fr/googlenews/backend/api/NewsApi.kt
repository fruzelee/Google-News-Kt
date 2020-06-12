package com.crevado.fr.googlenews.backend.api

import com.crevado.fr.googlenews.model.BaseModel
import com.crevado.fr.googlenews.model.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NewsApi {

    @GET("top-headlines")
    fun getPaymentTypes(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") page: Int
    ): Call<BaseModel<ArrayList<NewsData>>>

}