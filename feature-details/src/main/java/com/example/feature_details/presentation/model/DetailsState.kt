package com.example.feature_details.presentation.model

import com.example.core_ui_models.UiState

data class DetailsState(
    val model: DetailsModel = DetailsModel(),
    val isLoading: Boolean = false,
    val isExpanded: Boolean = false
) : UiState