package com.example.feature_login.presentation.model

import com.example.core.ui.UiState

data class LoginState(
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val isLoading: Boolean = false,
): UiState