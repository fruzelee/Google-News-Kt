package com.crevado.fr.googlenews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crevado.fr.googlenews.AppController
import com.crevado.fr.googlenews.backend.repository.NewsRepository
import com.crevado.fr.googlenews.model.BaseModel
import com.crevado.fr.googlenews.model.NewsData
import java.util.*
import javax.inject.Inject


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