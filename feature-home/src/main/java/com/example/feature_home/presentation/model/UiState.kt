package com.example.feature_home.presentation.model

import com.example.feature_home.domain.model.DomainDataSource

data class UiState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
    val category: String = "Wearable" // TODO
)