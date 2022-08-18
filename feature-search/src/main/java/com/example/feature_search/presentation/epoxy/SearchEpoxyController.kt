package com.example.feature_search.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_search.presentation.epoxy.model.ModelSearchItem
import com.example.feature_search.presentation.epoxy.model.ShimmerSearchItem
import com.example.feature_search.presentation.model.SearchState

class SearchEpoxyController(
    private val glide: RequestManager
) : TypedEpoxyController<SearchState>() {

    override fun buildModels(state: SearchState?) {
        if (state?.isLoading == true) {
            repeat(8) { index ->
                ShimmerSearchItem(index)
                    .id("shimmer_search_item_${index}")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            state.data.onEachIndexed { index, item ->
                ModelSearchItem(glide, item, index)
                    .id("search_item_${item.id}")
                    .addTo(this)
            }
        }
    }
}