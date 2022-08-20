package com.example.feature_home.presentation.epoxy.model

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.HomeItemBinding
import com.example.feature_home.domain.model.DomainDataSource

data class ModelHomeItem(
    private val item: DomainDataSource,
    private val glide: RequestManager,
    private val onProductClick: (String) -> Unit
) : ViewBindingKotlinModel<HomeItemBinding>(R.layout.home_item) {

    override fun HomeItemBinding.bind() {
        glide.load(item.image).into(ivProduct)
        tvTitle.text = item.model
        tvBody.text = item.model
        tvPrice.text = root.context.getString(com.example.ui_component.R.string.formatted_price, item.price)

        root.setOnClickListener { onProductClick(item.id) }
    }
}