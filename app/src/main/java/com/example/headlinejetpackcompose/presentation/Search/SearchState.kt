package com.example.headlinejetpackcompose.presentation.Search

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.headlinejetpackcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(


    val searchQuery :String,

    val articles : Flow<PagingData<Article>> ? = null
)