package com.example.feature_home.data.repository

import androidx.room.withTransaction
import com.example.core.helpers.networkBoundResource
import com.example.feature_home.data.local.HomeDatabase
import com.example.feature_home.data.mapper.toCacheDataSource
import com.example.feature_home.data.mapper.toDomainDataSource
import com.example.feature_home.data.remote.HomeFirestore
import com.example.feature_home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val db: HomeDatabase,
    private val api: HomeFirestore
) : HomeRepository {

    private val dao = db.dao

    override fun fetchData(category: String) = networkBoundResource(
        fetchCache = { dao.fetchCache(category).map { it.toDomainDataSource() } },
        fetchCloud = { api.fetchCloudData() },
        saveCache = { data ->
            db.withTransaction {
                dao.clearCache(category)
                dao.insertCache(data.map { it.toCacheDataSource() })
            }
        }
    )
}