package com.example.feature_favorites.presentation.epoxy.model

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_favorites.R
import com.example.feature_favorites.databinding.FavoritesItemBinding
import com.example.feature_favorites.domain.model.DomainDataSource

data class ModelFavoritesItem(
    val item: DomainDataSource,
    private val glide: RequestManager
) : ViewBindingKotlinModel<FavoritesItemBinding>(R.layout.favorites_item){

    override fun FavoritesItemBinding.bind() {
        tvModel.text = item.model
        tvPrice.text = item.price
        glide.load(item.img).into(ivProduct)
    }
}