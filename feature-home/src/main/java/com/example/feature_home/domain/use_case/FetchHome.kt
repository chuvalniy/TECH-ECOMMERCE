package com.example.feature_home.domain.use_case

import com.example.feature_home.domain.repository.HomeRepository

class FetchHome(
    private val repository: HomeRepository
) {

    fun execute(category: String) = repository.fetchData(category)
}