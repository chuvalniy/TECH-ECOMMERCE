package com.example.feature_search.presentation.model

import com.example.core.ui.UiText
import com.example.core_ui_models.UiSideEffect

sealed class SearchSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : SearchSideEffect()
    data class NavigateToDetails(val id: String) : SearchSideEffect()
    object NavigateBack : SearchSideEffect()
}