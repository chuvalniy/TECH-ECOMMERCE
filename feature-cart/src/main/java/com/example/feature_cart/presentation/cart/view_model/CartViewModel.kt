package com.example.feature_cart.presentation.cart.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.data_user_session.data.UserPreferences
import com.example.feature_cart.domain.model.DomainDataSource
import com.example.feature_cart.domain.use_case.DeleteCart
import com.example.feature_cart.domain.use_case.DeleteSingleCart
import com.example.feature_cart.domain.use_case.FetchCart
import com.example.feature_cart.domain.use_case.InsertCart
import com.example.feature_cart.presentation.cart.model.CartEvent
import com.example.feature_cart.presentation.cart.model.CartSideEffect
import com.example.feature_cart.presentation.cart.model.CartState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val fetchCart: FetchCart,
    private val deleteCart: DeleteCart,
    private val deleteSingleCart: DeleteSingleCart,
    private val insertCart: InsertCart,
    private val userPref: UserPreferences
) : BaseViewModel<CartEvent, CartState, CartSideEffect>(CartState()) {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            fetchCart.execute(userPref.fetchId()).onEachResource(
                onError = { showSnackbar(it) },
                onSuccess = { _state.value = _state.value.copy(data = it) },
                onLoading = { _state.value = _state.value.copy(isLoading = it) }
            ).launchIn(this)
        }
    }

    override fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.ItemSwiped -> itemSwiped(event.data)
            is CartEvent.UndoClicked -> undoClicked(event.data)
            is CartEvent.DialogPositiveButtonClicked -> dialogPositiveButtonClicked()
            is CartEvent.ClearCartButtonClicked -> clearCartButtonClicked()
            is CartEvent.CheckoutButtonClicked -> checkoutButtonClicked()
            is CartEvent.BackButtonClicked -> backButtonClicked()
        }
    }

    private fun itemSwiped(data: DomainDataSource) = viewModelScope.launch {
        deleteSingleCart.execute(userId = userPref.fetchId(), data = data).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = {
                showUndoSnackbar(it, data)
                fetchData()
            }
        ).launchIn(this)
    }

    private fun undoClicked(data: DomainDataSource) = viewModelScope.launch {
        insertCart.execute(userId = userPref.fetchId(), data = data).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = { fetchData() }
        ).launchIn(this)
    }

    private fun dialogPositiveButtonClicked() = viewModelScope.launch {
        deleteCart.execute(userId = userPref.fetchId(), data = _state.value.data).onEachResource(
            onError = { showSnackbar(it) },
            onSuccess = { fetchData() }
        ).launchIn(this)
    }

    private fun clearCartButtonClicked() {
        if (_state.value.data.isEmpty()) return
        viewModelScope.launch { _sideEffect.send(CartSideEffect.ShowAlertDialog) }
    }

    private fun checkoutButtonClicked() = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.NavigateToCheckout)
    }

    private fun backButtonClicked() = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.NavigateBack)
    }

    private fun showUndoSnackbar(message: UiText, data: DomainDataSource) = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.ShowUndoSnackbar(message, data))
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(CartSideEffect.ShowSnackbar(message))
    }
}