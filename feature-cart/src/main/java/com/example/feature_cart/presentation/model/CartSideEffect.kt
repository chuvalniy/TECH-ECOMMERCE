package com.example.feature_cart.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText

sealed class CartSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : CartSideEffect()
    object NavigateBack : CartSideEffect()
    object NavigateToCheckout : CartSideEffect()
    object NavigateToPayment : CartSideEffect()
}