package com.example.feature_home.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText

sealed class HomeSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText): HomeSideEffect()
    object NavigateToSearch : HomeSideEffect()
}