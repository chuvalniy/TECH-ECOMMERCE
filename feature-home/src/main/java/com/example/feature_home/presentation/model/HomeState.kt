package com.example.feature_home.presentation.model

import com.example.core_ui_models.UiState
import com.example.feature_home.domain.model.DomainDataSource

data class HomeState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
    val category: String = "Wearable" // TODO
) : com.example.core_ui_models.UiState