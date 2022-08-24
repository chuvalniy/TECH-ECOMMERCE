package com.example.feature_cart.data.mapper

import com.example.feature_cart.data.local.model.CacheDataSource
import com.example.feature_cart.data.remote.model.CloudDataSource
import com.example.feature_cart.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        model = model,
        img = img,
        price = price,
        quantity = quantity
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        model = model,
        img = img,
        price = price.toString(),
        quantity = quantity
    )
}

fun DomainDataSource.toCloudCartItem(): CloudDataSource {
    return CloudDataSource(
        id = id,
        model = model,
        img = img,
        price = price.toInt(),
        quantity = quantity
    )
}