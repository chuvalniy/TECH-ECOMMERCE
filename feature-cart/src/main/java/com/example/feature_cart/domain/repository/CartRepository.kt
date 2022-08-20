package com.example.feature_cart.domain.repository

import com.example.core.utils.Resource
import com.example.feature_cart.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun fetchData(): Flow<Resource<List<DomainDataSource>>>
}