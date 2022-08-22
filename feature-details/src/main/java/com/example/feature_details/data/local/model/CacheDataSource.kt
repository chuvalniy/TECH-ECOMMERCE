package com.example.feature_details.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_details.data.local.DetailsDatabase

@Entity(tableName = DetailsDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val modelFull: String,
    val price: Int,
    val rating: Float,
    val quantity: Int,
    val description: String,
    val images: List<String>,
)