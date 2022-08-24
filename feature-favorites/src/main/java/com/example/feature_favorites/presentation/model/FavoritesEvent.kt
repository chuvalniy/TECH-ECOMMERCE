package com.example.feature_favorites.presentation.model

import com.example.core.ui.UiEvent

sealed class FavoritesEvent : UiEvent {
    object BackButtonClicked : FavoritesEvent()
}