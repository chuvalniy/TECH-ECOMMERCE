package com.example.feature_cart.data.remote

import com.example.feature_cart.data.remote.model.CloudDataSource

interface CartFirestore {

    suspend fun fetchCloudData(): List<CloudDataSource>
}