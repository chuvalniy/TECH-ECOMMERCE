package com.example.feature_home.data.mapper

import com.example.feature_home.data.local.model.CacheDataSource
import com.example.feature_home.data.remote.model.CloudDataSource
import com.example.feature_home.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        brand = brand,
        model = model,
        modelFull = modelFull,
        category = category,
        description = description,
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
        description = description,
        price = price.toString(),
        image = image
    )
}