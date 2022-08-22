package com.example.feature_search.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.ShimmerSearchResultBinding

class ShimmerSearchResult :
    ViewBindingKotlinModel<ShimmerSearchResultBinding>(R.layout.shimmer_search_result) {

    override fun ShimmerSearchResultBinding.bind() {
        shimmerLayoutSearchResult.startShimmer()
    }

    override fun ShimmerSearchResultBinding.unbind() {
        shimmerLayoutSearchResult.stopShimmer()
    }
}