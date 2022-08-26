package com.example.feature_cart.presentation.cart.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_cart.presentation.cart.epoxy.model.ModelCartItem
import com.example.feature_cart.presentation.cart.epoxy.model.ShimmerCartItem
import com.example.feature_cart.presentation.cart.model.CartState

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
                ModelCartItem(item = item, glide = glide)
                    .id("cart_item_${item.id}")
                    .addTo(this)
            }
        }
    }
}