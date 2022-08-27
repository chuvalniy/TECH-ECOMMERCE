package com.example.feature_login.domain.repository

import com.example.core.utils.Resource
import com.example.feature_login.presentation.model.LoginSubState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun login(email: String, password: String, subState: LoginSubState): Flow<Resource<AuthResult>>

}