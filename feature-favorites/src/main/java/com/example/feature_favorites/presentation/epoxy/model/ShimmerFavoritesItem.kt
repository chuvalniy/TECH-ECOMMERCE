package com.example.feature_favorites.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_favorites.R
import com.example.feature_favorites.databinding.ShimmerFavoritesItemBinding

class ShimmerFavoritesItem :
    ViewBindingKotlinModel<ShimmerFavoritesItemBinding>(R.layout.shimmer_favorites_item) {

    override fun ShimmerFavoritesItemBinding.bind() {
        shimmerLayoutFavorites.startShimmer()
    }

    override fun ShimmerFavoritesItemBinding.unbind() {
        shimmerLayoutFavorites.stopShimmer()
    }
}