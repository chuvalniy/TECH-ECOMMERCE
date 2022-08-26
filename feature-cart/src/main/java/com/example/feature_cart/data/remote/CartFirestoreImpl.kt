package com.example.feature_cart.data.remote

import com.example.feature_cart.data.remote.model.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CartFirestoreImpl(
    private val firestore: FirebaseFirestore
) : CartFirestore {

    override suspend fun fetchCloudData(userId: String): List<CloudDataSource> {
        return firestore
            .collection(CART_COLLECTION)
            .document(userId)
            .collection(CART_ITEMS_COLLECTION)
            .get()
            .await()
            .toObjects(CloudDataSource::class.java)
    }

    override suspend fun insertCloudData(userId: String, data: CloudDataSource) {
        firestore
            .collection(CART_COLLECTION)
            .document(userId)
            .collection(CART_ITEMS_COLLECTION)
            .document(data.id)
            .set(data)
            .await()
    }

    override suspend fun deleteCloudData(userId: String, data: List<CloudDataSource>) {
        data.onEach { item ->
            firestore
                .collection(CART_COLLECTION)
                .document(userId)
                .collection(CART_ITEMS_COLLECTION)
                .document(item.id)
                .delete()
                .await()
        }
    }

    override suspend fun deleteSingleCloudData(userId: String, data: CloudDataSource) {
        firestore
            .collection(CART_COLLECTION)
            .document(userId)
            .collection(CART_ITEMS_COLLECTION)
            .document(data.id)
            .delete()
            .await()
    }

    private companion object {
        const val CART_COLLECTION = "cart"
        const val CART_ITEMS_COLLECTION = "items"
    }
}