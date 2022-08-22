package com.example.feature_login.data.repository

import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_login.data.api.LoginApi
import com.example.feature_login.domain.repository.LoginRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(
    private val api: LoginApi
) : LoginRepository {

    override fun login(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading(isLoading = true))

        val result = try {
            api.login(email, password)
        } catch (e: FirebaseAuthUserCollisionException) {
            emit(Resource.Error(error = UiText.DynamicString(e.localizedMessage ?: "")))
            null
        } catch (e: FirebaseAuthWeakPasswordException) {
            emit(Resource.Error(error = UiText.DynamicString(e.localizedMessage ?: "")))
            null
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(Resource.Error(error = UiText.DynamicString(e.localizedMessage ?: "")))
            null
        } catch (e: Exception) {
            emit(Resource.Error(error = UiText.DynamicString(e.localizedMessage ?: "")))
            null
        }

        result?.let { authResult ->
            emit(Resource.Success(authResult))
        }

        emit(Resource.Loading(isLoading = false))
    }

    override fun register(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading(isLoading = true))

        val result = try {
            api.register(email, password)
        } catch (e: Exception) {
            emit(Resource.Error(error = UiText.DynamicString(e.localizedMessage ?: "")))
            null
        }

        result?.let { authResult ->
            emit(Resource.Success(authResult))
        }

        emit(Resource.Loading(isLoading = false))
    }
}

