package com.example.feature_details.presentation.model

import com.example.core_ui_models.UiEvent

sealed class DetailsEvent : com.example.core_ui_models.UiEvent {
    object BackButtonClicked : DetailsEvent()
    object AddToCartButtonClicked : DetailsEvent()
    object AddToFavoriteButtonClicked : DetailsEvent()
}