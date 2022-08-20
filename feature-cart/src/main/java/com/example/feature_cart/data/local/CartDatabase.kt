package com.example.feature_cart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_cart.data.local.model.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
abstract class CartDatabase : RoomDatabase() {
    abstract val dao: CartDao

    companion object {
        const val DATABASE_NAME = "cart_cache"
    }
}