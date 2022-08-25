package com.example.feature_favorites.presentation.model

import com.example.core_ui_models.UiSideEffect
import com.example.core.ui.UiText
import com.example.feature_favorites.domain.model.DomainDataSource

sealed class FavoritesSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText): FavoritesSideEffect()
    object NavigateBack : FavoritesSideEffect()
    data class ShowUndoSnackbar(
        val message: UiText,
        val data: DomainDataSource
    ): FavoritesSideEffect()
}