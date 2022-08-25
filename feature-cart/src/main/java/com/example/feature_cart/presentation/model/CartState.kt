package com.example.feature_cart.presentation.model

import com.example.feature_cart.domain.model.DomainDataSource

data class CartState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
) : com.example.core_ui_models.UiState