package com.example.feature_details.data.repository

import androidx.room.withTransaction
import com.example.core.helpers.networkBoundResource
import com.example.feature_details.data.local.DetailsDatabase
import com.example.feature_details.data.mapper.toCacheDataSource
import com.example.feature_details.data.mapper.toDomainDataSource
import com.example.feature_details.data.remote.DetailsFirestore
import com.example.feature_details.domain.model.DomainDataSource
import com.example.feature_details.domain.repository.DetailsRepository

class DetailsRepositoryImpl(
    private val db: DetailsDatabase,
    private val api: DetailsFirestore
) : DetailsRepository {

    private val dao = db.dao

    override fun fetchData(id: String) = networkBoundResource(
        fetchCache = { dao.fetchCache(id)?.toDomainDataSource() },
        fetchCloud = { api.fetchCloudData(id) },
        saveCache = { data ->
            db.withTransaction {
                dao.clearCache(id)
                dao.insertCache(data.toCacheDataSource())
            }
        }
    )
}