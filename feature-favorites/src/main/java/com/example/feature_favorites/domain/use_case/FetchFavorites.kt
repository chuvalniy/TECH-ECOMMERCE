package com.example.feature_favorites.domain.use_case

import com.example.feature_favorites.domain.repository.FavoritesRepository

class FetchFavorites(
    private val repository: FavoritesRepository
) {

    fun execute(userId: String) = repository.fetchData(userId)
}