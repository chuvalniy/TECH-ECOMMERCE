package com.example.feature_cart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_cart.data.local.model.CacheDataSource

@Dao
interface CartDao {

    @Query("SELECT * FROM ${CartDatabase.DATABASE_NAME}")
    suspend fun fetchCache(): List<CacheDataSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: List<CacheDataSource>)

    @Query("DELETE FROM ${CartDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}
