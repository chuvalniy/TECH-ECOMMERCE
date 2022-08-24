package com.example.feature_details.presentation.model

import com.example.core_ui_models.UiSideEffect
import com.example.core.ui.UiText

sealed class DetailsSideEffect : com.example.core_ui_models.UiSideEffect {
    data class ShowSnackbar(val message: UiText) : DetailsSideEffect()
    object NavigateBack : DetailsSideEffect()
}
