package com.example.feature_login.data.api

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

interface LoginApi {

    suspend fun login(email: String, password: String): AuthResult

    suspend fun register(email: String, password: String): AuthResult

    class Base(private val firebaseAuth: FirebaseAuth) : LoginApi {

        override suspend fun login(email: String, password: String): AuthResult =
            firebaseAuth.signInWithEmailAndPassword(email, password).await()

        override suspend fun register(email: String, password: String): AuthResult =
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }
}