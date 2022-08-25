package com.example.feature_favorites.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_favorites.data.local.model.CacheDataSource


@Database(
    entities = [CacheDataSource::class],
    version = 1
)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract val dao: FavoritesDao

    companion object {
        const val DATABASE_NAME = "favorites_cache"
    }
}