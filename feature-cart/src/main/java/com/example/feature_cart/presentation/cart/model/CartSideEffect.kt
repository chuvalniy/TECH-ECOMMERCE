package com.example.feature_cart.presentation.cart.model

import com.example.core.ui.UiText
import com.example.core_ui_models.UiSideEffect
import com.example.feature_cart.domain.model.DomainDataSource

sealed class CartSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : CartSideEffect()
    data class ShowUndoSnackbar(val message: UiText, val data: DomainDataSource) : CartSideEffect()
    object ShowAlertDialog : CartSideEffect()
    object NavigateBack : CartSideEffect()
    object NavigateToCheckout : CartSideEffect()
}