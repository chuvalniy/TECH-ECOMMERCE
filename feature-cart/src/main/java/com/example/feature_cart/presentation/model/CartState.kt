package com.example.feature_cart.presentation.model

import com.example.core.ui.UiState
import com.example.feature_cart.domain.model.DomainDataSource

data class CartState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
) : UiState