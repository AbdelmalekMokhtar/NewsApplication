package com.example.newsapp.data.network

import com.example.newsapp.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = "f782fd8776cf40e79251cf3e5f81bf1e"
    ): Response<NewsResponse>

}

//https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=f782fd8776cf40e79251cf3e5f81bf1e

