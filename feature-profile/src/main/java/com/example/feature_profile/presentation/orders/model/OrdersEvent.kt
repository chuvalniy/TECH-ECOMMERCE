package com.example.feature_profile.presentation.orders.model

import com.example.core_ui_models.UiEvent

sealed class OrdersEvent : UiEvent {
    object BackButtonClicked : OrdersEvent()
    object StartOrderingButtonClicked : OrdersEvent()
}