package com.example.feature_profile.presentation.profile.model

import com.example.core_ui_models.UiState
import com.example.feature_profile.domain.model.DomainDataSource

data class ProfileState(
    val data: DomainDataSource? = null,
    val isLoading: Boolean = false,
) : UiState