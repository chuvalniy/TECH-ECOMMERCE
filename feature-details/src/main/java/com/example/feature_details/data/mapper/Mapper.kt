package com.example.feature_details.data.mapper

import com.example.feature_details.data.local.model.CacheDataSource
import com.example.feature_details.data.remote.model.CloudDataSource
import com.example.feature_details.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        modelFull = modelFull,
        price = price,
        description = description,
        images = images,
        rating = rating,
        quantity = quantity
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        description = description,
        modelFull = modelFull,
        price = price.toString(),
        images = images,
        rating = rating,
        quantity = quantity
    )
}