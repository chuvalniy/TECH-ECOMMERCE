package com.example.feature_cart.presentation.epoxy.model

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_cart.R
import com.example.feature_cart.databinding.CartItemBinding
import com.example.feature_cart.domain.model.DomainDataSource

data class ModelCartItem(
    val item: DomainDataSource,
    private val glide: RequestManager
) : ViewBindingKotlinModel<CartItemBinding>(R.layout.cart_item) {

    override fun CartItemBinding.bind() {
        glide.load(item.img).into(ivProduct)
        tvModel.text = item.model
        tvPrice.text = root.context.getString(R.string.formatted_price_float, item.price)
    }
}