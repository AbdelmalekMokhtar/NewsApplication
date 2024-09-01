package com.example.newsapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.appConstants.AppConstant
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _news : MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(
        ResourceState.Loading())
    val news: StateFlow<ResourceState<NewsResponse>> = _news

    init {
        fetchNews(AppConstant.COUNTRY)
    }

     fun fetchNews(country: String) {
        viewModelScope.launch(Dispatchers.IO) {   //Api collaboration in IO thread
            newsRepository.getNewsHeadline(country)
                .collectLatest { newsResponse ->
                    _news.value = newsResponse
                }
        }
    }
}
