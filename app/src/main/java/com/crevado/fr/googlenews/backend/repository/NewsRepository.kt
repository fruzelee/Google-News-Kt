package com.crevado.fr.googlenews.backend.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.crevado.fr.googlenews.AppController
import com.crevado.fr.googlenews.backend.api.NewsApi
import com.crevado.fr.googlenews.di.ApiComponent
import com.crevado.fr.googlenews.model.BaseModel
import com.crevado.fr.googlenews.model.NewsData
import com.crevado.fr.googlenews.room.NewsDao
import com.crevado.fr.googlenews.util.Constants
import com.crevado.fr.googlenews.util.Utils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class NewsRepository {

    private val newsListLiveData: MutableLiveData<BaseModel<ArrayList<NewsData>>> = MutableLiveData()

    init {
        val apiComponent: ApiComponent = AppController.app.mApiComponent
        apiComponent.inject(this)
    }

    @Inject
    lateinit var newsApi: NewsApi

    @Inject
    lateinit var newsDao: NewsDao

    fun fetchNewsList(): MutableLiveData<BaseModel<ArrayList<NewsData>>> {

        // Fetch offline news
        fetchNewsOffline()

        if (!Utils.isNetworkAvailable()) {
            Toast.makeText(
                AppController.app.applicationContext,
                "Internet not connected",
                Toast.LENGTH_LONG
            ).show()
        }

        val postListInfo: Call<BaseModel<ArrayList<NewsData>>> =
            newsApi.getPaymentTypes("us", Constants.NEWS_API_KEY, 50)
        postListInfo.enqueue(object : Callback<BaseModel<ArrayList<NewsData>>> {
            override fun onFailure(call: Call<BaseModel<ArrayList<NewsData>>>, t: Throwable) {
//                val errorData = BaseModel("error", "error loading list", arrayListOf<NewsData>())
//                newsListLiveData.postValue(errorData)
                Toast.makeText(
                    AppController.app.applicationContext,
                    "Error loading data, Try again later",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<BaseModel<ArrayList<NewsData>>>,
                response: Response<BaseModel<ArrayList<NewsData>>>
            ) {
                if (response.code() == 200) {
                    // Insert news in DB
                    insertNews(response.body()?.articles)
                    //newsListLiveData.postValue(response.body());
                } else {
//                  val errorData = BaseModel("error", "error loading list", arrayListOf<NewsData>())
//                  newsListLiveData.postValue(errorData)
                    Toast.makeText(
                        AppController.app.applicationContext,
                        "Error loading data, Try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
        return newsListLiveData
    }

    // Live data triggered when all records from DB loaded
    private fun fetchNewsOffline() {
        doAsync {
            val result = newsDao.getNews()
            uiThread {
                val newsArrayList = ArrayList<NewsData>()
                newsArrayList.addAll(result)
                val dbData = BaseModel("ok", "", newsArrayList)
                newsListLiveData.postValue(dbData)
            }
        }
    }

    fun insertNews(newsList: ArrayList<NewsData>?) {
        doAsync {

            var needsUpdate = false
            if (newsList != null) {
                for (item in newsList) {
                    val inserted = newsDao.insertNews(item)
                    if (inserted == -1L) {
                        val updated = newsDao.insertNews(item)
                        if (updated > 0) {
                            needsUpdate = true
                        }
                    } else {
                        needsUpdate = true
                    }
                }
            }

            uiThread {
                if (needsUpdate)
                    fetchNewsOffline()
            }
        }
    }


}