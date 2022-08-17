package com.example.feature_search.data.repository

import androidx.room.withTransaction
import com.example.core.helpers.networkBoundResource
import com.example.feature_search.data.local.SearchDatabase
import com.example.feature_search.data.mapper.toCacheDataSource
import com.example.feature_search.data.mapper.toDomainDataSource
import com.example.feature_search.data.remote.SearchFirestore
import com.example.feature_search.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val db: SearchDatabase,
    private val api: SearchFirestore
) : SearchRepository {

    private val dao = db.dao

    override fun fetchCache(searchQuery: String) = networkBoundResource(
        fetchCache = { dao.fetchCache(searchQuery).map { it.toDomainDataSource() } },
        fetchCloud = { api.fetchCloudData() },
        saveCache = { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.map { it.toCacheDataSource() })
            }
        }
    )
}