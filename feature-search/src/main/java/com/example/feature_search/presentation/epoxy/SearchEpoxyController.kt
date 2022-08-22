package com.example.feature_search.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_search.presentation.epoxy.model.ModelSearchItem
import com.example.feature_search.presentation.epoxy.model.ModelSearchResult
import com.example.feature_search.presentation.epoxy.model.ShimmerSearchItem
import com.example.feature_search.presentation.epoxy.model.ShimmerSearchResult
import com.example.feature_search.presentation.model.SearchState

class SearchEpoxyController(
    private val glide: RequestManager,
    private val onProductClick: (String) -> Unit
) : TypedEpoxyController<SearchState>() {

    override fun buildModels(state: SearchState?) {
        if (state?.isLoading == true) {
            ShimmerSearchResult()
                .id("shimmer_search_result")
                .addTo(this)

            repeat(8) { index ->
                ShimmerSearchItem()
                    .id("shimmer_search_item_${index}")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            if (state.data.isNotEmpty()) {
                ModelSearchResult(state.data.size).id("search_result").addTo(this)
            }

            state.data.onEach { item ->
                ModelSearchItem(
                    glide = glide,
                    item = item,
                    onProductClick = onProductClick
                )
                    .id("search_item_${item.id}")
                    .addTo(this)
            }
        }
    }
}