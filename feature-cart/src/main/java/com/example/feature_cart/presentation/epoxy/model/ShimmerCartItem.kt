package com.example.feature_cart.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_cart.R
import com.example.feature_cart.databinding.ShimmerCartItemBinding

class ShimmerCartItem : ViewBindingKotlinModel<ShimmerCartItemBinding>(R.layout.shimmer_cart_item) {

    override fun ShimmerCartItemBinding.bind() {
        shimmerLayoutCart.startShimmer()
    }

    override fun ShimmerCartItemBinding.unbind() {
        shimmerLayoutCart.stopShimmer()

    }
}