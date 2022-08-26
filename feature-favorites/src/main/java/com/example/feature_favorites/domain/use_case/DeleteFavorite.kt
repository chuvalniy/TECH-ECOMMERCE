package com.example.feature_favorites.domain.use_case

import com.example.feature_favorites.domain.model.DomainDataSource
import com.example.feature_favorites.domain.repository.FavoritesRepository

class DeleteFavorite(
    private val repository: FavoritesRepository
) {

    fun execute(userId: String, data: DomainDataSource) = repository.deleteData(userId, data)
}