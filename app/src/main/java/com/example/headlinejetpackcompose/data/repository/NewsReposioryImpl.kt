package com.example.headlinejetpackcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.headlinejetpackcompose.data.remote.NewsApi
import com.example.headlinejetpackcompose.data.remote.NewsPaginSource
import com.example.headlinejetpackcompose.data.remote.SearchNewsPaginSource
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsReposioryImpl(
    val newsApi: NewsApi,
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPaginSource(
                    newsAoi = newsApi,
                    source = sources.joinToString(
                        separator = ","
                    )
                )
            }
        ).flow


    }

    override fun searchNews(
        query: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPaginSource(
                    searchQuery = query,
                    newsAoi = newsApi,
                    source = sources.joinToString(
                        separator = ","
                    )
                )
            }
        ).flow


    }
}