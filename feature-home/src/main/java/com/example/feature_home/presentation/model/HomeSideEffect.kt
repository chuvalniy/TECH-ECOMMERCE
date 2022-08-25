package com.example.feature_home.presentation.model

import com.example.core_ui_models.UiSideEffect
import com.example.core.ui.UiText

sealed class HomeSideEffect : com.example.core_ui_models.UiSideEffect {
    data class ShowSnackbar(val message: UiText): HomeSideEffect()
    data class NavigateToDetails(val id: String): HomeSideEffect()
    object NavigateToSearch : HomeSideEffect()
}