package com.example.feature_cart.data.remote

import com.example.feature_cart.data.remote.model.CloudDataSource

interface CartFirestore {

    suspend fun fetchCloudData(userId: String): List<CloudDataSource>

    suspend fun insertCloudData(userId: String, data: CloudDataSource)

    suspend fun deleteCloudData(userId: String, data: List<CloudDataSource>)

    suspend fun deleteSingleCloudData(userId: String, data: CloudDataSource)
}