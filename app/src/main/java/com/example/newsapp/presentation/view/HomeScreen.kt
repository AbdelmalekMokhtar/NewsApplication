package com.example.newsapp.presentation.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.viewModel.NewsViewModel
import com.example.newsapp.ui.components.Loader
import com.example.newsapp.ui.components.NewsRowComponent
import com.example.newsapp.utilities.ResourceState
import androidx.hilt.navigation.compose.hiltViewModel

const val TAG = "HomeScreen"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
){
    val newsResponse = newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){
        100
    }
    VerticalPager(state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) {page ->

        when(newsResponse.value) {

            is ResourceState.Loading -> {
                Log.d(TAG,"Inside_Loading")
                Loader()
            }
            is ResourceState.Success -> {
                val response = (newsResponse.value as ResourceState.Success).data
                Log.d(TAG, "Inside_Success ${response.status} = ${response.totalResults}")

                if(response.articles.isNotEmpty()) {
                    NewsRowComponent(page, response.articles[page])
                }
            }

            is ResourceState.Error -> {
                val error = (newsResponse.value as ResourceState.Error)
                Log.d(TAG,"Inside_Error $error")
            }
        }

    }



}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
