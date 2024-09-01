package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.dataSource.NewsDataSource
import com.example.newsapp.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

//    suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
//        return newsApiService.getNewsHeadline(country)
//    }
    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
    return flow {
        emit(ResourceState.Loading())
        val response = newsDataSource.getNewsHeadline(country)
        if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
        } else {
            emit(ResourceState.Error("Error Fetching News Data"))
        }

    }.catch { e ->
        emit(ResourceState.Error(e.localizedMessage ?: "Unknown Error"))
    }
}

}
