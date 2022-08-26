package com.example.feature_profile.data.repository

import androidx.room.withTransaction
import com.example.core.helpers.networkBoundResource
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_profile.data.local.ProfileDatabase
import com.example.feature_profile.data.mapper.toCacheDataSource
import com.example.feature_profile.data.mapper.toCloudDataSource
import com.example.feature_profile.data.mapper.toDomainDataSource
import com.example.feature_profile.data.remote.ProfileFirestore
import com.example.feature_profile.domain.model.DomainDataSource
import com.example.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val db: ProfileDatabase,
    private val api: ProfileFirestore
) : ProfileRepository {

    private val dao = db.dao

    override fun fetchData(userId: String): Flow<Resource<DomainDataSource>> = networkBoundResource(
        fetchCache = { dao.fetchCache()?.toDomainDataSource() },
        fetchCloud = { api.fetchCloudData(userId) },
        saveCache = { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.toCacheDataSource())
            }
        }
    )

    override fun updateData(userId: String, data: DomainDataSource): Flow<Resource<UiText>> =
        networkBoundResource {
            api.updateCloudData(userId, data.toCloudDataSource())
            UiText.DynamicString("Success") // TODo
        }
}