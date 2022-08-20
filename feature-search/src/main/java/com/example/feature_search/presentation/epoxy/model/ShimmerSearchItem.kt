package com.example.feature_search.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.ShimmerSearchItemBinding

data class ShimmerSearchItem(
    private val index: Int
) : ViewBindingKotlinModel<ShimmerSearchItemBinding>(R.layout.shimmer_search_item) {

    override fun ShimmerSearchItemBinding.bind() {

        shimmerLayoutSearch.startShimmer()
    }

    override fun ShimmerSearchItemBinding.unbind() {
        shimmerLayoutSearch.stopShimmer()
    }

//    private fun ShimmerSearchItemBinding.setupPadding() {
//        when (index % 2) {
//            0 -> root.setPadding(
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_32),
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_16),
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_16),
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_16)
//            )
//            1 -> root.setPadding(
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_16),
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_16),
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_32),
//                root.context.resources.getDimensionPixelSize(com.example.ui_component.R.dimen.layout_space_16)
//            )
//        }
//    }
}