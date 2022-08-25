package com.example.feature_favorites.data.remote

import com.example.feature_favorites.data.remote.model.CloudDataSource

interface FavoritesFirestore {

    suspend fun fetchCloudData(userId: String): List<CloudDataSource>

    suspend fun insertData(userId: String, data: CloudDataSource)

    suspend fun deleteData(userId: String, data: CloudDataSource)
}