package com.crevado.fr.googlenews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crevado.fr.googlenews.AppController
import com.crevado.fr.googlenews.backend.repository.NewsRepository
import com.crevado.fr.googlenews.model.BaseModel
import com.crevado.fr.googlenews.model.NewsData
import java.util.*
import javax.inject.Inject

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
class NewsViewModel : ViewModel() {

    private var newsLiveData: MutableLiveData<BaseModel<ArrayList<NewsData>>>

    init {
        this.newsLiveData = MutableLiveData()
        AppController.app.mApiComponent.inject(this)
    }

    @Inject
    lateinit var mRepository: NewsRepository

    fun getNewsList(): MutableLiveData<BaseModel<ArrayList<NewsData>>> {
        newsLiveData = mRepository.fetchNewsList()
        return newsLiveData
    }

}