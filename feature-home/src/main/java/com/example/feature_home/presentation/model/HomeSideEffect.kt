package com.example.feature_home.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText

sealed class HomeSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText): HomeSideEffect()
    data class NavigateToDetails(val id: String): HomeSideEffect()
    object NavigateToSearch : HomeSideEffect()
}