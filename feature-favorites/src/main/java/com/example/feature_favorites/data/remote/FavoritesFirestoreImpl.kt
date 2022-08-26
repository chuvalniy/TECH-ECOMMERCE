package com.example.feature_favorites.data.remote

import android.util.Log
import com.example.feature_favorites.data.remote.model.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FavoritesFirestoreImpl(
    private val firestore: FirebaseFirestore
) : FavoritesFirestore {

    override suspend fun fetchCloudData(userId: String): List<CloudDataSource> {
        return firestore
            .collection(FAVORITES_COLLECTION)
            .document(userId)
            .collection(FAVORITES_COLLECTION_ITEMS)
            .get()
            .await()
            .toObjects(CloudDataSource::class.java)
    }

    override suspend fun insertData(userId: String, data: CloudDataSource) {
        firestore
            .collection(FAVORITES_COLLECTION)
            .document(userId)
            .collection(FAVORITES_COLLECTION_ITEMS)
            .document(data.id)
            .set(data)
            .await()
    }

    override suspend fun deleteData(userId: String, data: CloudDataSource) {
        firestore
            .collection(FAVORITES_COLLECTION)
            .document(userId)
            .collection(FAVORITES_COLLECTION_ITEMS)
            .document(data.id)
            .delete()
            .await()
    }

    override suspend fun isDataExist(userId: String, id: String): Boolean {
        val item = firestore
            .collection(FAVORITES_COLLECTION)
            .document(userId)
            .collection(FAVORITES_COLLECTION_ITEMS)
            .document(id)
            .get()
            .await()
            .toObject(CloudDataSource::class.java)

        return item != null
    }

    private companion object {
        const val FAVORITES_COLLECTION = "favorites"
        const val FAVORITES_COLLECTION_ITEMS = "items"
    }
}