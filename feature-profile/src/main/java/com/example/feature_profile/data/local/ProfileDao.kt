package com.example.feature_profile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_profile.data.local.model.CacheDataSource

@Dao
interface ProfileDao {

    @Query("SELECT * FROM ${ProfileDatabase.DATABASE_NAME}")
    suspend fun fetchCache(): CacheDataSource?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: CacheDataSource)

    @Query("DELETE FROM ${ProfileDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}