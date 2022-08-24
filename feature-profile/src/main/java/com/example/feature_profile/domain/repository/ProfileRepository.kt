package com.example.feature_profile.domain.repository

import com.example.core.utils.Resource
import com.example.feature_profile.data.local.model.CacheDataSource
import com.example.feature_profile.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun fetchData(userId: String): Flow<Resource<DomainDataSource>>
}