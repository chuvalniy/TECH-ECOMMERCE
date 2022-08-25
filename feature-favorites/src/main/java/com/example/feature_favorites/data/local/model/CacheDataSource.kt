package com.example.feature_favorites.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_favorites.data.local.FavoritesDatabase

@Entity(tableName = FavoritesDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val model: String,
    val img: String,
    val price: Int,
)