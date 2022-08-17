package com.example.feature_home.domain.repository

import com.example.core.utils.Resource
import com.example.feature_home.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchCache(): Flow<Resource<List<DomainDataSource>>>
}