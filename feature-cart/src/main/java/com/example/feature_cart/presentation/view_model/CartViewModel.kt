package com.example.feature_cart.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.data_user_session.data.UserSession
import com.example.feature_cart.domain.model.DomainDataSource
import com.example.feature_cart.domain.repository.CartRepository
import com.example.feature_cart.presentation.model.CartEvent
import com.example.feature_cart.presentation.model.CartSideEffect
import com.example.feature_cart.presentation.model.CartState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartRepository,
    private val userSession: UserSession
) : BaseViewModel<CartEvent, CartState, CartSideEffect>(CartState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.fetchData(userSession.fetchUserId()).onEach { result ->
                when (result) {
                    is Resource.Error -> showSnackbar(
                        result.error
                            ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
                    )
                    is Resource.Loading ->
                        _state.value = _state.value.copy(isLoading = result.isLoading)
                    is Resource.Success -> result.data?.let { data ->
                        _state.value = _state.value.copy(
                            data = data,
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    override fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.ItemSwiped -> itemSwiped(event.data)
            is CartEvent.UndoClicked -> undoClicked(event.data)
            is CartEvent.BackButtonClicked -> backButtonClicked()
            is CartEvent.CheckoutButtonClicked -> checkoutButtonClicked()
            is CartEvent.ClearCartButtonClicked -> clearCartButtonClicked()
            is CartEvent.ConfirmAndPayButtonClicked -> confirmAndPayButtonClicked()
        }
    }

    private fun itemSwiped(data: DomainDataSource) = viewModelScope.launch {
        repository.deleteData(
            userId = userSession.fetchUserId(),
            data = data
        )

        _sideEffect.send(CartSideEffect.ShowUndoSnackbar(data))
        fetchData()
    }

    private fun undoClicked(data: DomainDataSource) = viewModelScope.launch {
        repository.insertData(
            userId = userSession.fetchUserId(),
            data = data
        )

        fetchData()
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.NavigateBack)
    }

    private fun checkoutButtonClicked() = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.NavigateToCheckout)
    }

    private fun clearCartButtonClicked() = viewModelScope.launch {
        repository.deleteAllData(
            userId = userSession.fetchUserId(),
            data = _state.value.data
        )

        fetchData()
    }

    private fun confirmAndPayButtonClicked() = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.NavigateToPayment)
    }


    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.ShowSnackbar(message))
    }
}