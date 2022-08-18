package com.example.feature_cart.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.utils.Resource
import com.example.feature_cart.domain.model.DomainDataSource
import com.example.feature_cart.domain.repository.CartRepository
import com.example.feature_cart.presentation.model.CartEvent
import com.example.feature_cart.presentation.model.CartSideEffect
import com.example.feature_cart.presentation.model.CartState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartRepository
) : BaseViewModel<CartEvent, CartState, CartSideEffect>(CartState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.fetchData().onEach { result ->
                when (result) {
                    is Resource.Error -> TODO()
                    is Resource.Loading ->
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    is Resource.Success -> {
                        processSuccessState(result)
                    }
                }
            }.launchIn(this)
        }
    }

    private fun processSuccessState(result: Resource<List<DomainDataSource>>) {
        result.data?.let { data ->
            _state.value = _state.value.copy(data = data)
        }
    }

    override fun onEvent(event: CartEvent) {
        when (event) {
            CartEvent.BackButtonClicked -> backButtonClicked()
            CartEvent.CheckoutButtonClicked -> TODO()
            CartEvent.ClearCartButtonClicked -> TODO()
            CartEvent.DecreaseQuantityButtonClicked -> TODO()
            CartEvent.IncreaseQuantityButtonClicked -> TODO()
        }
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.NavigateBack)
    }
}