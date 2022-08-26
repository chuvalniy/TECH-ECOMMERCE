package com.example.feature_home.data.remote

import com.example.feature_home.data.remote.model.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeFirestoreImpl(
    private val firestore: FirebaseFirestore
): HomeFirestore {

    override suspend fun fetchCloudData(): List<CloudDataSource> {
        return firestore
            .collection(FIRESTORE_PRODUCTS_TABLE)
            .get()
            .await()
            .toObjects(CloudDataSource::class.java)
    }


    // TODO ?
//    override suspend fun insertCloudData(data: CloudDataSource) {
//        firestore
//            .collection(FIRESTORE_PRODUCTS_TABLE)
//            .document(data.id)
//            .set(data)
//            .await()
//    }
//
//    override suspend fun deleteCloudData(data: CloudDataSource) {
//        firestore
//            .collection(FIRESTORE_PRODUCTS_TABLE)
//            .document(data.id)
//            .delete()
//            .await()
//    }

    private companion object {
        const val FIRESTORE_PRODUCTS_TABLE = "products"
    }
}