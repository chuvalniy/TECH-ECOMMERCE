package com.example.feature_home.presentation.epoxy.model

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.HomeItemBinding
import com.example.feature_home.domain.model.DomainDataSource

data class ModelHomeItem(
    private val item: DomainDataSource,
    private val glide: RequestManager
) : ViewBindingKotlinModel<HomeItemBinding>(R.layout.home_item) {

    override fun HomeItemBinding.bind() {
        TODO("Not yet implemented")
    }
}