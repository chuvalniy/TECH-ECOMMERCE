package com.example.feature_details.presentation.model

import com.example.core_ui_models.UiState
import com.example.feature_details.domain.model.DomainDataSource

data class DetailsState(
    val data: DomainDataSource = DomainDataSource("", "", "", "", 0F, 0, emptyList()),
    val isLoading: Boolean = false
) : com.example.core_ui_models.UiState