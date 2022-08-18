package com.example.feature_home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_home.data.local.HomeDao
import com.example.feature_home.data.local.model.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
abstract class HomeDatabase : RoomDatabase() {
    abstract val dao: HomeDao

    companion object {
        const val DATABASE_NAME = "home_cache"
    }
}