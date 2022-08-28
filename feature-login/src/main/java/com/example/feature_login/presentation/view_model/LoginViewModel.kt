package com.example.feature_login.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.core.extension.onEachResource
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.data_user_session.data.UserPreferences
import com.example.feature_login.domain.use_case.Login
import com.example.feature_login.domain.use_case.ValidateEmail
import com.example.feature_login.domain.use_case.ValidatePassword
import com.example.feature_login.domain.use_case.ValidateRepeatedPassword
import com.example.feature_login.presentation.model.LoginEvent
import com.example.feature_login.presentation.model.LoginSideEffect
import com.example.feature_login.presentation.model.LoginState
import com.example.feature_login.presentation.model.LoginSubState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val login: Login,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val userPref: UserPreferences
) : BaseViewModel<LoginEvent, LoginState, LoginSideEffect>(LoginState()) {

    init {
        if (userPref.fetchId().isNotBlank()) {
            navigateToHome()
        }
    }

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginButtonClicked -> loginButtonClicked()
            is LoginEvent.EmailChanged -> emailChanged(event.email)
            is LoginEvent.PasswordChanged -> passwordChanged(event.password)
            is LoginEvent.RepeatedPasswordChanged -> repeatedPasswordChanged(event.password)
            is LoginEvent.ActionButtonClicked -> actionButtonClicked(event.subState)
        }
    }

    private fun loginButtonClicked(
        email: String = _state.value.email,
        password: String = _state.value.password,
        subState: LoginSubState = _state.value.subState
    ) {
        if (!isValidationSuccessful()) return

        viewModelScope.launch {
            login.execute(email, password, subState).onEachResource(
                onLoading = {
                    _state.value = _state.value.copy(isLoading = it)
                },
                onError = { showSnackbar(it) },
                onSuccess = { authResult ->
                    authResult.user?.uid?.let {
                        userPref.updateId(it)
                        navigateToHome()
                    }
                }
            ).launchIn(this)
        }
    }

    private fun emailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    private fun passwordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    private fun repeatedPasswordChanged(password: String) {
        _state.value = _state.value.copy(repeatedPassword = password)
    }

    private fun actionButtonClicked(subState: LoginSubState) {
        _state.value = _state.value.copy(subState = subState)
        when (subState) {
            LoginSubState.Login -> navigateToLogin()
            LoginSubState.Register -> navigateToRegister()
        }
    }

    private fun navigateToRegister() = viewModelScope.launch {
        _sideEffect.send(LoginSideEffect.NavigateToRegister)
    }

    private fun navigateToLogin() = viewModelScope.launch {
        _sideEffect.send(LoginSideEffect.NavigateToLogin)
    }

    private fun isValidationSuccessful(): Boolean {
        val email = validateEmail.execute(_state.value.email)
        if (!email.successful) {
            showSnackbar(
                email.errorMessage
                    ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
            )
            return false
        }

        val password = validatePassword.execute(_state.value.password)
        if (!password.successful) {
            showSnackbar(
                password.errorMessage
                    ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
            )
            return false
        }

        if (_state.value.subState == LoginSubState.Register) {
            val repeatedPassword = validateRepeatedPassword.execute(
                password = _state.value.password,
                repeatedPassword = _state.value.repeatedPassword
            )
            if (!repeatedPassword.successful) {
                showSnackbar(
                    message = repeatedPassword.errorMessage ?: UiText.StringResource(
                        com.example.ui_component.R.string.unexpected_error
                    )
                )
                return false
            }
        }

        return true
    }

    private fun showSnackbar(message: UiText) = viewModelScope.launch {
        _sideEffect.send(LoginSideEffect.ShowSnackbar(message = message))
    }

    private fun navigateToHome() = viewModelScope.launch {
        _sideEffect.send(LoginSideEffect.NavigateToHome)
    }
}