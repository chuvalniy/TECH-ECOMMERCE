package com.example.feature_cart.domain.use_case

import com.example.feature_cart.domain.model.DomainDataSource
import com.example.feature_cart.domain.repository.CartRepository

class DeleteCart(
    private val repository: CartRepository
) {

    fun execute(userId: String, data: List<DomainDataSource>) = repository.deleteData(userId, data)
}