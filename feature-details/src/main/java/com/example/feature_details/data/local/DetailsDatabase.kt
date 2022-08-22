package com.example.feature_details.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_details.data.local.converters.Converters
import com.example.feature_details.data.local.model.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DetailsDatabase : RoomDatabase() {
    abstract val dao: DetailsDao

    companion object {
        const val DATABASE_NAME = "details_cache"
    }
}