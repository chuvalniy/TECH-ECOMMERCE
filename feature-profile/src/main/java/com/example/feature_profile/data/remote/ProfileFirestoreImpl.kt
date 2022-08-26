package com.example.feature_profile.data.remote

import android.util.Log
import com.example.feature_profile.data.remote.model.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProfileFirestoreImpl(
    private val firestore: FirebaseFirestore
) : ProfileFirestore {

    override suspend fun fetchCloudData(userId: String): CloudDataSource? {
        Log.d("TAGTAG", "fectch cloud")

        val data = firestore.collection(USER_COLLECTION)
            .document(userId)
            .get()
            .await()
            .toObject(CloudDataSource::class.java)

        Log.d("TAGTAG", data.toString())

        return data
    }

    override suspend fun updateCloudData(userId: String, data: CloudDataSource) {
        firestore.collection(USER_COLLECTION)
            .document(userId)
            .set(data)
            .await()
    }

    private companion object {
        const val USER_COLLECTION = "users"
    }
}