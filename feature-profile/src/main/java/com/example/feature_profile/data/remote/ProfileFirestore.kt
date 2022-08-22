package com.example.feature_profile.data.remote

import com.example.feature_profile.data.remote.model.CloudDataSource

interface ProfileFirestore {

    suspend fun fetchCloudData(userId: String): CloudDataSource?
}