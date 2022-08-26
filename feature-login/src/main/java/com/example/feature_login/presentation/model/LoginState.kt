package com.example.feature_login.presentation.model

import com.example.core_ui_models.UiState

enum class LoginSubState {
    Login, Register
}

data class LoginState(
    val subState: LoginSubState = LoginSubState.Login,
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val isLoading: Boolean = false,
): UiState