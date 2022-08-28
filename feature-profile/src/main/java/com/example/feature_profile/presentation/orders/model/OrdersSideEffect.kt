package com.example.feature_profile.presentation.orders.model

import com.example.core.ui.UiText
import com.example.core_ui_models.UiSideEffect

sealed class OrdersSideEffect : UiSideEffect {
    data class ShowSnackBar(val message: UiText): OrdersSideEffect()
    object NavigateBack : OrdersSideEffect()
    object NavigateToSearch : OrdersSideEffect()
}