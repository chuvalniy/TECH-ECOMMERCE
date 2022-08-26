package com.example.feature_details.presentation.model

import com.example.feature_details.domain.model.DomainDataSource

data class DetailsModel(
    val data: DomainDataSource = DomainDataSource("", "", "", "", 0F, emptyList()),
    val isFavorites: Boolean = false,
)