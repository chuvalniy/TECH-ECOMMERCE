package com.example.feature_home.data.remote

import com.example.feature_home.data.remote.model.CloudDataSource

class HomeFirestoreImpl: HomeFirestore {

    override suspend fun fetchCloudData(): List<CloudDataSource> {
        TODO("Not yet implemented")
    }
}