package com.example.feature_login.data.api

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

interface LoginApi {

    suspend fun login(email: String, password: String): AuthResult

    suspend fun register(email: String, password: String): AuthResult

    class Base(
        private val firebaseAuth: FirebaseAuth,
        private val firebaseFirestore: FirebaseFirestore,
    ) : LoginApi {

        override suspend fun login(email: String, password: String): AuthResult =
            firebaseAuth.signInWithEmailAndPassword(email, password).await()

        // TODO
        override suspend fun register(email: String, password: String): AuthResult {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            val user = mapOf(
                "userId" to result.user?.uid!!,
                "email" to email
            )

            firebaseFirestore
                .collection(USER_COLLECTION)
                .document(result.user?.uid!!)
                .set(user)
                .await()

            return result
        }

        private companion object {
            private const val USER_COLLECTION = "users"
        }
    }
}