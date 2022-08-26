package com.example.feature_profile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_profile.data.local.converters.Converters
import com.example.feature_profile.data.local.model.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class ProfileDatabase : RoomDatabase() {

    abstract val dao: ProfileDao

    companion object {
        const val DATABASE_NAME = "profile_cache"
    }
}