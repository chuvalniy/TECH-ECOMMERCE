package com.example.feature_login.presentation.model

import com.example.core.ui.UiEvent

sealed class LoginEvent : UiEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class RepeatedPasswordChanged(val password: String) : LoginEvent()
    object RegisterButtonClicked : LoginEvent()
    object LoginButtonClicked : LoginEvent()
    object CreateNewAccountButtonClicked : LoginEvent()
    object AlreadyHaveAccountButtonClicked : LoginEvent()
}