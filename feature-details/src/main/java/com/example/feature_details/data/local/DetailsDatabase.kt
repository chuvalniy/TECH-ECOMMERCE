package com.example.feature_details.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_details.data.local.model.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
abstract class DetailsDatabase : RoomDatabase() {
    abstract val dao: DetailsDao

    companion object {
        const val DATABASE_NAME = "details_cache"
    }
}