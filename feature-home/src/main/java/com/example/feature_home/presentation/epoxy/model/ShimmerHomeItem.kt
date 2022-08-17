package com.example.feature_home.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.ShimmerHomeItemBinding

class ShimmerHomeItem : ViewBindingKotlinModel<ShimmerHomeItemBinding>(R.layout.shimmer_home_item) {

    override fun ShimmerHomeItemBinding.bind() {
        shimmerLayoutHome.startShimmer()
    }

    override fun ShimmerHomeItemBinding.unbind() {
        shimmerLayoutHome.stopShimmer()
    }
}