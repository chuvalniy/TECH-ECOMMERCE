package com.example.feature_details.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_details.data.local.model.CacheDataSource

@Dao
interface DetailsDao {

    @Query("SELECT * FROM ${DetailsDatabase.DATABASE_NAME} WHERE id = :id")
    suspend fun fetchCache(id: String): CacheDataSource?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: CacheDataSource)

    @Query("DELETE FROM ${DetailsDatabase.DATABASE_NAME} WHERE id = :id")
    suspend fun clearCache(id: String)
}