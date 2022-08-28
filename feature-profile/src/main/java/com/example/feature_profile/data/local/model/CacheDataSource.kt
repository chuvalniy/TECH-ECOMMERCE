package com.example.feature_profile.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_profile.data.local.ProfileDatabase
import com.example.feature_profile.data.remote.model.CloudOrderHistoryItem

@Entity(tableName = ProfileDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val userId: String,
    val email: String,
    val image: String,
    val firstName: String,
    val lastName: String,
    val shippingAddresses: List<CacheShippingAddress>,
    val orderHistory: List<CacheOrderHistory>,
    val phoneNumber: String,
)

data class CacheShippingAddress(
    val country: String,
    val city: String,
    val street1: String,
    val street2: String,
    val state: String,
    val zip: String,
    val phone: String,
)

data class CacheOrderHistory(
    val id: String,
    val status: String,
    val createdAt: String,
    val purchasedItems: List<CacheOrderHistoryItem>,
)

data class CacheOrderHistoryItem(
    val id: String,
    val price: Int,
    val image: String,
    val modelFull: String,
)