package com.example.feature_profile.presentation.orders.model

import com.example.core_ui_models.UiState
import com.example.feature_profile.domain.model.DomainOrderHistory

data class OrdersState(
    val data: List<DomainOrderHistory> = emptyList(),
): UiState