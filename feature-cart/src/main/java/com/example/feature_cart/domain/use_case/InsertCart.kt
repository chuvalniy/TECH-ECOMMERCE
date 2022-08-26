package com.example.feature_cart.domain.use_case

import com.example.feature_cart.domain.model.DomainDataSource
import com.example.feature_cart.domain.repository.CartRepository

class InsertCart(
    private val repository: CartRepository
) {

    fun execute(userId: String, data: DomainDataSource) = repository.insertData(userId, data)
}