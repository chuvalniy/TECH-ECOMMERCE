package com.example.feature_search.presentation.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.ModelSearchResultBinding

data class ModelSearchResult(
    private val totalResults: Int
) : ViewBindingKotlinModel<ModelSearchResultBinding>(R.layout.model_search_result) {

    override fun ModelSearchResultBinding.bind() {
        tvSearchResult.text = root.context.getString(R.string.found_results, totalResults.toString())
    }
}