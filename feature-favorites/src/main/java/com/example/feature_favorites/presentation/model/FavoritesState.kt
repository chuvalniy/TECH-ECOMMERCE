package com.example.feature_favorites.presentation.model

import com.example.core.ui.UiState
import com.example.feature_favorites.domain.model.DomainDataSource

data class FavoritesState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false
) : UiState