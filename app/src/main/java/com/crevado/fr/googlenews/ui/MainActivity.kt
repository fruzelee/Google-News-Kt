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
