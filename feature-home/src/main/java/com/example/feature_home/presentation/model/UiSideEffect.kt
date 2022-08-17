package com.example.feature_home.presentation.model

import com.example.core.ui.UiText

sealed class UiSideEffect {
    data class ShowSnackbar(val message: UiText): UiSideEffect()
    object NavigateToSearch : UiSideEffect()
}