package com.example.feature_cart.data.remote

import com.example.feature_cart.data.remote.model.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CartFirestoreImpl : CartFirestore {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchCloudData(): List<CloudDataSource> {
        return firestore
            .collection("products")
            .get()
            .await()
            .toObjects(CloudDataSource::class.java)
    }
}