package com.example.feature_profile.data.mapper

import com.example.feature_profile.data.local.model.CacheDataSource
import com.example.feature_profile.data.local.model.CacheShippingAddress
import com.example.feature_profile.data.remote.model.CloudDataSource
import com.example.feature_profile.data.remote.model.CloudShippingAddress
import com.example.feature_profile.domain.model.DomainDataSource
import com.example.feature_profile.domain.model.DomainShippingAddress

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        email = email,
        image = image,
        firstName = firstName,
        lastName = lastName,
        shippingAddresses = shippingAddresses.map { it.toCacheShippingAddress() },
        phoneNumber = phoneNumber
    )
}

fun CloudShippingAddress.toCacheShippingAddress(): CacheShippingAddress {
    return CacheShippingAddress(
        country = country,
        city = city,
        street1 = street1,
        street2 = street2,
        state = state,
        zip = zip,
        phone = phone
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        email = email,
        image = image,
        firstName = firstName,
        lastName = lastName,
        shippingAddresses = shippingAddresses.map { it.toDomainShippingAddress() },
        phoneNumber = phoneNumber
    )
}

fun CacheShippingAddress.toDomainShippingAddress(): DomainShippingAddress {
    return DomainShippingAddress(
        country = country,
        city = city,
        street1 = street1,
        street2 = street2,
        state = state,
        zip = zip,
        phone = phone
    )
}