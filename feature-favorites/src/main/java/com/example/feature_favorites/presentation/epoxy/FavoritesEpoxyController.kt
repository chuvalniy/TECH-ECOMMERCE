package com.example.feature_favorites.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_favorites.presentation.epoxy.model.ModelFavoritesItem
import com.example.feature_favorites.presentation.epoxy.model.ShimmerFavoritesItem
import com.example.feature_favorites.presentation.model.FavoritesState

class FavoritesEpoxyController(
    private val glide: RequestManager
) : TypedEpoxyController<FavoritesState>() {

    override fun buildModels(state: FavoritesState?) {
        if (state?.isLoading == true) {
            repeat(4) {
                ShimmerFavoritesItem()
                    .id("shimmer_favorites_item_$it")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            state.data.onEach { item ->
                ModelFavoritesItem(item = item, glide = glide)
                    .id("favorites_item_${item.id}")
                    .addTo(this)
            }
        }
    }
}