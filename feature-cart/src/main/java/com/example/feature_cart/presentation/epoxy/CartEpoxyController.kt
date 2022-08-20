package com.example.feature_cart.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_cart.presentation.epoxy.model.ModelCartItem
import com.example.feature_cart.presentation.epoxy.model.ShimmerCartItem
import com.example.feature_cart.presentation.model.CartState

class CartEpoxyController(
    private val glide: RequestManager
) : TypedEpoxyController<CartState>() {

    override fun buildModels(state: CartState?) {
        if (state?.isLoading == true) {
            repeat(5) { index ->
                ShimmerCartItem()
                    .id("shimmer_cart_item_$index")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            state.data.onEach { item ->
                ModelCartItem(glide, item)
                    .id("cart_item_${item.id}")
                    .addTo(this)
            }
        }
    }
}