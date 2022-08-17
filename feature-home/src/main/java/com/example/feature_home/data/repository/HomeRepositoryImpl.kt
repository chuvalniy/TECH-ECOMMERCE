package com.example.feature_home.data.repository

import androidx.room.withTransaction
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_home.data.local.HomeDatabase
import com.example.feature_home.data.mapper.toCacheDataSource
import com.example.feature_home.data.mapper.toDomainDataSource
import com.example.feature_home.data.remote.HomeFirestore
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val db: HomeDatabase,
    private val api: HomeFirestore
) : HomeRepository {

    private val dao = db.dao

    override fun fetchCache(): Flow<Resource<List<DomainDataSource>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val cache = dao.fetchCache()
        emit(Resource.Success(cache.map { it.toDomainDataSource() }))

        val response = try {
            api.fetchCloudData()
        } catch (e: Exception) { // Todo
            emit(Resource.Error(error = UiText.DynamicString(e.localizedMessage)))
            null
        }

        response?.let { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.map { it.toCacheDataSource() })
            }

            emit(Resource.Success(dao.fetchCache().map { it.toDomainDataSource() }))
            emit(Resource.Loading(isLoading = false))
        }
    }
}