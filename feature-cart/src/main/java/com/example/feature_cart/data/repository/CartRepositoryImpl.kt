package com.example.feature_cart.data.repository

import androidx.room.withTransaction
import com.example.core.helpers.networkBoundResource
import com.example.feature_cart.data.local.CartDatabase
import com.example.feature_cart.data.mapper.toCacheDataSource
import com.example.feature_cart.data.mapper.toCloudCartItem
import com.example.feature_cart.data.mapper.toDomainDataSource
import com.example.feature_cart.data.remote.CartFirestore
import com.example.feature_cart.domain.model.DomainDataSource
import com.example.feature_cart.domain.repository.CartRepository

class CartRepositoryImpl(
    private val db: CartDatabase,
    private val api: CartFirestore
) : CartRepository {

    private val dao = db.dao

    override fun fetchData(userId: String) = networkBoundResource(
        fetchCache = { dao.fetchCache().map { it.toDomainDataSource() } },
        fetchCloud = { api.fetchCloudData(userId) },
        saveCache = { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.map { it.toCacheDataSource() })
            }
        }
    )

    override suspend fun insertData(userId: String, item: DomainDataSource) {
        api.insertCloudData(userId, item.toCloudCartItem()) // TODO()
    }
}