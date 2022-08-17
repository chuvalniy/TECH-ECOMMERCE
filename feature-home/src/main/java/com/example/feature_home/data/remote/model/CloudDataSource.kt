package com.example.feature_home.data.remote.model

data class CloudDataSource(
    val id: String = "",
    val brand: String = "",
    val model: String = "",
    val modelFull: String = "",
    val category: String = "",
    val description: String = "",
    val price: Int = 0,
    val images: List<String> = emptyList(),
)