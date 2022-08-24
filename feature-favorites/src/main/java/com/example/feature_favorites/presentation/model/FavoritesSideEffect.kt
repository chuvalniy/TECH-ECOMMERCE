package com.example.feature_favorites.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText

sealed class FavoritesSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText): FavoritesSideEffect()
    object NavigateBack : FavoritesSideEffect()
}