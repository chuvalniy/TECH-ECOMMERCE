package com.example.feature_search.presentation.model

import com.example.core_ui_models.UiState
import com.example.feature_search.domain.model.DomainDataSource

data class SearchState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
) : UiState