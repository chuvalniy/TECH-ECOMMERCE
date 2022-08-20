package com.example.feature_cart.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_cart.data.local.CartDatabase

@Entity(tableName = CartDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val modelFull: String,
    val price: Int,
    val image: String,
)