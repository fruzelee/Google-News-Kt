package com.crevado.fr.googlenews.backend.api

import com.crevado.fr.googlenews.model.BaseModel
import com.crevado.fr.googlenews.model.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
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
interface NewsApi {

    @GET("top-headlines")
    fun getPaymentTypes(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") page: Int
    ): Call<BaseModel<ArrayList<NewsData>>>

}