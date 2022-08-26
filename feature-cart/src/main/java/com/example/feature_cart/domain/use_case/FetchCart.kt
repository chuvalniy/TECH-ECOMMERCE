package com.example.feature_cart.domain.use_case

import com.example.feature_cart.domain.repository.CartRepository

class FetchCart(
    private val repository: CartRepository
) {

    fun execute(userId: String) = repository.fetchData(userId)
}