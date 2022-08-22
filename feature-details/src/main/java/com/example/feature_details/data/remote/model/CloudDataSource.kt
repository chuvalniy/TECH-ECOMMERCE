package com.example.feature_details.data.remote.model

data class CloudDataSource(
    val id: String = "",
    val brand: String = "",
    val model: String = "",
    val modelFull: String = "",
    val rating: Float = 0F,
    val quantity: Int = 0,
    val description: String = "",
    val price: Int = 0,
    val images: List<String> = emptyList(),
)