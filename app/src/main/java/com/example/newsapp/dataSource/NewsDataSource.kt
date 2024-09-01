package com.example.newsapp.dataSource

import com.example.newsapp.data.model.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    suspend fun getNewsHeadline(country: String): Response<NewsResponse>
}