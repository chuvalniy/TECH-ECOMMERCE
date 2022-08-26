package com.example.feature_cart.presentation.checkout.view_model

import androidx.lifecycle.SavedStateHandle
import com.example.feature_cart.domain.repository.CartRepository

class CheckoutViewModel(
    private val repository: CartRepository,
    private val savedState: SavedStateHandle
) {
}