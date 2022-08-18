package com.example.feature_cart.data.mapper

import com.example.feature_cart.data.local.model.CacheDataSource
import com.example.feature_cart.data.remote.model.CloudDataSource
import com.example.feature_cart.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        modelFull = modelFull,
        price = price,
        image = images.first()
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        modelFull = modelFull,
        price = price.toString(),
        image = image
    )
}