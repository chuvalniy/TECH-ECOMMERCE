package com.example.feature_search.presentation.epoxy.model

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.SearchItemBinding
import com.example.feature_search.domain.model.DomainDataSource

data class ModelSearchItem(
    private val glide: RequestManager,
    private val item: DomainDataSource,
    private val index: Int,
) : ViewBindingKotlinModel<SearchItemBinding>(R.layout.search_item) {

    override fun SearchItemBinding.bind() {
        setupPadding()

        tvModel.text = item.model
        tvBrand.text = item.brand
        tvPrice.text = root.context.getString(R.string.from_price, item.price)
        glide.load(item.image).into(ivProduct)
    }

    private fun SearchItemBinding.setupPadding() {
        when (index % 2) {
            0 -> layoutSearchItem.setPadding(
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_medium),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small)
            )
            1 -> layoutSearchItem.setPadding(
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_medium),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small)
            )
        }
    }
}