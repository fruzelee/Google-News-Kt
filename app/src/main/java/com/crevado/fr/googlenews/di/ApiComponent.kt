package com.crevado.fr.googlenews.di

import com.crevado.fr.googlenews.backend.api.NewsApi
import com.crevado.fr.googlenews.backend.repository.NewsRepository
import com.crevado.fr.googlenews.viewmodel.NewsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiHelper::class, AppModule::class, DBModule::class])
interface ApiComponent {

    val newsApi: NewsApi

    fun inject(repo: NewsRepository)
    fun inject(newsVM: NewsViewModel)
}