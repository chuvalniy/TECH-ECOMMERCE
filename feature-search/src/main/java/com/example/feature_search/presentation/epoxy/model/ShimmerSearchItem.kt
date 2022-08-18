package com.example.feature_search.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.SearchItemBinding
import com.example.feature_search.databinding.ShimmerSearchItemBinding

data class ShimmerSearchItem(
    private val index: Int
) : ViewBindingKotlinModel<ShimmerSearchItemBinding>(R.layout.shimmer_search_item) {

    override fun ShimmerSearchItemBinding.bind() {
        setupPadding()

        shimmerLayoutSearch.startShimmer()
    }

    override fun ShimmerSearchItemBinding.unbind() {
        shimmerLayoutSearch.stopShimmer()
    }

    private fun ShimmerSearchItemBinding.setupPadding() {
        when (index % 2) {
            0 -> root.setPadding(
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_medium),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small)
            )
            1 -> root.setPadding(
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_medium),
                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_small)
            )
        }
    }
}