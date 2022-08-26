package com.example.feature_profile.presentation.profile.model

import com.example.core_ui_models.UiState
import com.example.feature_profile.domain.model.DomainDataSource

data class ProfileState(
    val data: DomainDataSource = DomainDataSource(
        userId = "",
        email = "",
        image = "",
        firstName = "Unknown",
        lastName = "User",
        shippingAddresses = emptyList(),
        phoneNumber = ""
    ),
    val isLoading: Boolean = false,
) : UiState