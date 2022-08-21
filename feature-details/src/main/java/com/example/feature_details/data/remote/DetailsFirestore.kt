package com.example.feature_details.data.remote

import com.example.feature_details.data.remote.model.CloudDataSource

interface DetailsFirestore {

    suspend fun fetchCloudData(id: String): CloudDataSource?
}