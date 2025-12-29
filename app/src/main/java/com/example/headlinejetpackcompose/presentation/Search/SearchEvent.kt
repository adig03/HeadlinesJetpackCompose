package com.example.headlinejetpackcompose.presentation.Search

sealed class SearchEvent {


    data class UpdateSearchQuery(val searchQuery : String) : SearchEvent()


    object SearchNews : SearchEvent()
}



