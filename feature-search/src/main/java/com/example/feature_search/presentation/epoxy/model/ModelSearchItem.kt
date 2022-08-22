package com.example.feature_search.presentation.epoxy.model

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.SearchItemBinding
import com.example.feature_search.domain.model.DomainDataSource

data class ModelSearchItem(
    private val glide: RequestManager,
    private val item: DomainDataSource,
    private val onProductClick: (String) -> Unit
) : ViewBindingKotlinModel<SearchItemBinding>(R.layout.search_item) {

    override fun SearchItemBinding.bind() {
        tvTitle.text = item.modelFull
        tvPrice.text = root.context.getString(R.string.from_price, item.price)
        glide.load(item.image).into(ivProduct)

        cvSearchItem.setOnClickListener { onProductClick(item.id) }
    }
}