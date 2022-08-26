package com.example.feature_home.data.remote

import com.example.feature_home.data.remote.model.CloudDataSource

interface HomeFirestore {

    suspend fun fetchCloudData(): List<CloudDataSource>

//    suspend fun insertCloudData(data: CloudDataSource)
//
//    suspend fun deleteCloudData(data: CloudDataSource)
}