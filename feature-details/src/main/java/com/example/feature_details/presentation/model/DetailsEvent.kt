package com.example.feature_details.presentation.model

import com.example.core.ui.UiEvent

sealed class DetailsEvent : UiEvent {
    object BackButtonClicked : DetailsEvent()
    object AddToCartButtonClicked : DetailsEvent()
    object AddToFavoriteButtonClicked : DetailsEvent()
}