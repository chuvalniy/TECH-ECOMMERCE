package com.example.feature_profile.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_profile.data.local.ProfileDatabase

@Entity(tableName = ProfileDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val email: String,
    val image: String,
    val firstName: String,
    val lastName: String,
    val shippingAddresses: List<CacheShippingAddress>,
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