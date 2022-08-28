package com.example.feature_profile.presentation.orders.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.feature_profile.presentation.orders.model.OrdersEvent
import com.example.feature_profile.presentation.orders.model.OrdersSideEffect
import com.example.feature_profile.presentation.orders.model.OrdersState
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val savedState: SavedStateHandle
) : BaseViewModel<OrdersEvent, OrdersState, OrdersSideEffect>(OrdersState()) {


    override fun onEvent(event: OrdersEvent) {
        when (event) {
            is OrdersEvent.BackButtonClicked -> backButtonClicked()
            is OrdersEvent.StartOrderingButtonClicked -> startOrderingButtonClicked()
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(OrdersSideEffect.NavigateBack)
    }

    private fun startOrderingButtonClicked() = viewModelScope.launch {
        _sideEffect.send(OrdersSideEffect.NavigateToSearch)
    }
}