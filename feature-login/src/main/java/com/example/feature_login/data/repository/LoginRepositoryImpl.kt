package com.example.feature_login.data.repository

import com.example.core.helpers.networkBoundResource
import com.example.core.utils.Resource
import com.example.feature_login.data.api.LoginApi
import com.example.feature_login.domain.repository.LoginRepository
import com.example.feature_login.presentation.model.LoginSubState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(
    private val api: LoginApi,
) : LoginRepository {

    override fun login(
        email: String,
        password: String,
        subState: LoginSubState
    ): Flow<Resource<AuthResult>> = networkBoundResource {
        when (subState) {
            LoginSubState.Login -> api.login(email, password)
            LoginSubState.Register -> api.register(email, password)
        }
    }
}

