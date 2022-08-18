package com.example.feature_search.data.mapper

import com.example.feature_search.data.local.model.CacheDataSource
import com.example.feature_search.data.remote.model.CloudDataSource
import com.example.feature_search.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        brand = brand,
        model = model,
        modelFull = modelFull,
        category = category,
        price = price,
        image = images.first()
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        brand = brand,
        model = model,
        modelFull = modelFull,
        category = category,
        price = price.toString(),
        image = image
    )
}