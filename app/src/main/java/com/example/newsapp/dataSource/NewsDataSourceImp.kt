package com.example.newsapp.dataSource

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.network.NewsApiService
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImp @Inject constructor(
private val newsApiService: NewsApiService
) : NewsDataSource {
    override suspend fun getNewsHeadline(country: String): Response<NewsResponse>{
        return newsApiService.getNewsHeadline(country)
    }

}