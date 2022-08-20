package com.example.feature_details.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText

sealed class DetailsSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : DetailsSideEffect()
    object NavigateBack : DetailsSideEffect()
}
