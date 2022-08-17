package com.example.feature_home.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_home.data.local.HomeDatabase

@Entity(tableName = HomeDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val brand: String,
    val model: String,
    val modelFull: String,
    val category: String,
    val description: String,
    val price: Int,
    val image: String,
)
