package com.example.feature_search.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.ShimmerSearchItemBinding

class ShimmerSearchItem :
    ViewBindingKotlinModel<ShimmerSearchItemBinding>(R.layout.shimmer_search_item) {

    override fun ShimmerSearchItemBinding.bind() {

        shimmerLayoutSearch.startShimmer()
    }

    override fun ShimmerSearchItemBinding.unbind() {
        shimmerLayoutSearch.stopShimmer()
    }

}