package com.example.feature_search.domain.use_case

import com.example.feature_search.domain.repository.SearchRepository

class SearchData(
    private val repository: SearchRepository
) {

    fun execute(searchQuery: String) = repository.searchData(searchQuery)
}