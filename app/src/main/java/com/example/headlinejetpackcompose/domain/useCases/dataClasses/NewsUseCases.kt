package com.example.headlinejetpackcompose.domain.useCases.dataClasses

import com.example.headlinejetpackcompose.domain.useCases.GetNews
import com.example.headlinejetpackcompose.domain.useCases.SearchNews

data class NewsUseCases(
    val getNews: GetNews,

    val searchNews: SearchNews,
)
