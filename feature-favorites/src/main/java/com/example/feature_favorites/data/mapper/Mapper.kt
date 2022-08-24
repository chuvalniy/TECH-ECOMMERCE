package com.example.feature_favorites.data.mapper

import com.example.feature_favorites.data.local.model.CacheDataSource
import com.example.feature_favorites.data.remote.model.CloudDataSource
import com.example.feature_favorites.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        model = model,
        img = img,
        price = price
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        model = model,
        img = img,
        price = price.toString(),
    )
}

fun DomainDataSource.toCloudDataSource(): CloudDataSource {
    return CloudDataSource(
        id = id,
        model = model,
        img = img,
        price = price.toInt(),
    )
}