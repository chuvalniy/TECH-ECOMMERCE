package com.example.feature_search.data.remote

import com.example.feature_search.data.remote.model.CloudDataSource

interface SearchFirestore {

    suspend fun fetchCloudData(): List<CloudDataSource>
}