package com.example.feature_profile.data.mapper

import com.example.feature_profile.data.local.model.CacheDataSource
import com.example.feature_profile.data.local.model.CacheOrderHistory
import com.example.feature_profile.data.local.model.CacheOrderHistoryItem
import com.example.feature_profile.data.local.model.CacheShippingAddress
import com.example.feature_profile.data.remote.model.CloudDataSource
import com.example.feature_profile.data.remote.model.CloudOrderHistory
import com.example.feature_profile.data.remote.model.CloudOrderHistoryItem
import com.example.feature_profile.data.remote.model.CloudShippingAddress
import com.example.feature_profile.domain.model.DomainDataSource
import com.example.feature_profile.domain.model.DomainOrderHistory
import com.example.feature_profile.domain.model.DomainOrderHistoryItem
import com.example.feature_profile.domain.model.DomainShippingAddress

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        userId = userId,
        email = email,
        image = image,
        firstName = firstName,
        lastName = lastName,
        shippingAddresses = shippingAddresses.map { it.toCacheShippingAddress() },
        orderHistory = orderHistory.map { it.toCacheOrderHistory() },
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

fun CloudOrderHistory.toCacheOrderHistory(): CacheOrderHistory {
    return CacheOrderHistory(
        id = id,
        status = status,
        createdAt = createdAt,
        purchasedItems = purchasedItems.map { it.toCacheOrderHistoryItem() }
    )
}

fun CloudOrderHistoryItem.toCacheOrderHistoryItem(): CacheOrderHistoryItem {
    return CacheOrderHistoryItem(
        id = id,
        price = price,
        image = image,
        modelFull = modelFull,
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        userId = userId,
        email = email,
        image = image,
        firstName = firstName,
        lastName = lastName,
        shippingAddresses = shippingAddresses.map { it.toDomainShippingAddress() },
        orderHistory = orderHistory.map { it.toDomainOrderHistory() },
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

fun CacheOrderHistory.toDomainOrderHistory(): DomainOrderHistory {
    return DomainOrderHistory(
        id  = id,
        status = status,
        purchasedItems = purchasedItems.map { it.toDomainOrderHistoryItem() },
        createdAt = createdAt,
    )
}

fun CacheOrderHistoryItem.toDomainOrderHistoryItem(): DomainOrderHistoryItem {
    return DomainOrderHistoryItem(
        id = id,
        price = price,
        image = image,
        modelFull = modelFull
    )
}

fun DomainDataSource.toCloudDataSource(): CloudDataSource {
    return CloudDataSource(
        userId = userId,
        email = email,
        image = image,
        firstName = firstName,
        lastName = lastName,
        shippingAddresses = shippingAddresses.map { it.toCloudShippingAddress() },
        phoneNumber = phoneNumber
    )
}

fun DomainShippingAddress.toCloudShippingAddress(): CloudShippingAddress {
    return CloudShippingAddress(
        country = country,
        city = city,
        street1 = street1,
        street2 = street2,
        state = state,
        zip = zip,
        phone = phone
    )
}