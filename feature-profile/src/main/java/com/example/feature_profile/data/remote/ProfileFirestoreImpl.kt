package com.example.feature_profile.data.remote

import com.example.feature_profile.data.remote.model.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProfileFirestoreImpl(
    private val firestore: FirebaseFirestore
) : ProfileFirestore {

    override suspend fun fetchCloudData(userId: String): CloudDataSource? {
        return firestore.collection(USER_COLLECTION)
            .document(userId)
            .get()
            .await()
            .toObject(CloudDataSource::class.java)
    }

    private companion object {
        const val USER_COLLECTION = "users"
    }
}