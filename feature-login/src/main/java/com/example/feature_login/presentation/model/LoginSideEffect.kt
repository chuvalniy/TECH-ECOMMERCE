package com.example.feature_login.presentation.model

import com.example.core.ui.UiSideEffect
import com.example.core.ui.UiText

sealed class LoginSideEffect : UiSideEffect {
    data class ShowSnackbar(val message: UiText) : LoginSideEffect()
    object NavigateToRegister : LoginSideEffect()
    object NavigateToLogin : LoginSideEffect()
    object NavigateToHome : LoginSideEffect()
}