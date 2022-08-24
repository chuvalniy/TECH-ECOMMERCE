package com.example.feature_favorites.data.repository

import androidx.room.withTransaction
import com.example.core.helpers.networkBoundResource
import com.example.feature_favorites.data.local.FavoritesDatabase
import com.example.feature_favorites.data.mapper.toCacheDataSource
import com.example.feature_favorites.data.mapper.toCloudDataSource
import com.example.feature_favorites.data.mapper.toDomainDataSource
import com.example.feature_favorites.data.remote.FavoritesFirestore
import com.example.feature_favorites.domain.model.DomainDataSource
import com.example.feature_favorites.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val db: FavoritesDatabase,
    private val api: FavoritesFirestore
) : FavoritesRepository {

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

    override suspend fun insertData(userId: String, data: DomainDataSource) {
        api.insertData(userId, data.toCloudDataSource()) // TODO()
    }

    override suspend fun deleteData(userId: String, data: DomainDataSource) {
        api.deleteData(userId, data.toCloudDataSource())
    }
}