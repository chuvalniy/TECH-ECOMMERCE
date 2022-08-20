package com.example.feature_cart.presentation.model

import com.example.core.ui.UiEvent

sealed class CartEvent : UiEvent {
    object BackButtonClicked : CartEvent()
    object CheckoutButtonClicked : CartEvent()
    object ClearCartButtonClicked : CartEvent()
    object ConfirmAndPayButtonClicked : CartEvent()
    object IncreaseQuantityButtonClicked : CartEvent()
    object DecreaseQuantityButtonClicked : CartEvent()
}