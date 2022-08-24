package com.example.feature_favorites.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText
import com.example.feature_favorites.domain.model.DomainDataSource

sealed class FavoritesSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText): FavoritesSideEffect()
    data class ShowUndoSnackbar(val data: DomainDataSource): FavoritesSideEffect()
    object NavigateBack : FavoritesSideEffect()
}