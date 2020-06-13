package com.crevado.fr.googlenews.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.crevado.fr.googlenews.R
import com.crevado.fr.googlenews.adapter.NewsListAdapter
import com.crevado.fr.googlenews.base.BaseActivity
import com.crevado.fr.googlenews.databinding.ActivityMainBinding
import com.crevado.fr.googlenews.model.NewsData
import com.crevado.fr.googlenews.viewmodel.NewsViewModel

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
class MainActivity : BaseActivity<ActivityMainBinding>(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarText("Google News")
        hideBack()
        onLoadingSwipeRefresh()
        init()
    }

    private fun onLoadingSwipeRefresh() {
        try {
            binding.swipeRefreshLayout.post { getNewsApi() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun init() {
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
    }

    private fun getNewsApi() {
        val newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.getNewsList().observe(this, Observer { newsData ->
            binding.swipeRefreshLayout.isRefreshing = false
            hideProgress()
            showData()
            setAdapter(newsData.articles)
        })
    }

    // setting adapter
    private fun setAdapter(newsList: ArrayList<NewsData>) {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = NewsListAdapter(this@MainActivity, newsList)

            // onclick adapter
            (adapter as NewsListAdapter).onItemClick = { url ->
                val i = Intent(this@MainActivity, DetailActivity::class.java)
                i.putExtra("url", url)
                startActivity(i)
            }
        }
    }

    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = true
        getNewsApi()
    }

}
