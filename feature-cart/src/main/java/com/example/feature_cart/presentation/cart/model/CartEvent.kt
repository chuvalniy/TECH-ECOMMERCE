package com.example.feature_cart.presentation.cart.model

import com.example.core_ui_models.UiEvent
import com.example.feature_cart.domain.model.DomainDataSource

sealed class CartEvent : UiEvent {
    data class ItemSwiped(val data: DomainDataSource): CartEvent()
    data class UndoClicked(val data: DomainDataSource): CartEvent()
    object DialogPositiveButtonClicked : CartEvent()
    object BackButtonClicked : CartEvent()
    object CheckoutButtonClicked : CartEvent()
    object ClearCartButtonClicked : CartEvent()
}