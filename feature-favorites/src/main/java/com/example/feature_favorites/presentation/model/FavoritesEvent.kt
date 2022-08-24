package com.example.feature_favorites.presentation.model

import com.example.core.ui.UiEvent
import com.example.feature_favorites.domain.model.DomainDataSource

sealed class FavoritesEvent : UiEvent {
    object BackButtonClicked : FavoritesEvent()
    data class ItemSwiped(val item: DomainDataSource) : FavoritesEvent()
    data class UndoClicked(val item: DomainDataSource): FavoritesEvent()
}