package com.example.feature_login.presentation.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.ui.BaseViewModel
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.data_user_session.data.UserSession
import com.example.feature_login.R
import com.example.feature_login.domain.use_case.Login
import com.example.feature_login.domain.use_case.ValidateEmail
import com.example.feature_login.domain.use_case.ValidatePassword
import com.example.feature_login.domain.use_case.ValidateRepeatedPassword
import com.example.feature_login.presentation.model.LoginEvent
import com.example.feature_login.presentation.model.LoginSideEffect
import com.example.feature_login.presentation.model.LoginState
import com.example.feature_login.presentation.model.LoginSubState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel(
    private val login: Login,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val userSession: UserSession
) : BaseViewModel<LoginEvent, LoginState, LoginSideEffect>(LoginState()) {

    init {
        if (userSession.fetchUserId().isNotBlank()) {
            navigateToHome()
        }
    }

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.AlreadyHaveAccountButtonClicked -> alreadyHaveAccountButtonClicked()
            is LoginEvent.CreateNewAccountButtonClicked -> createNewAccountButtonClicked()
            is LoginEvent.LoginButtonClicked -> loginButtonClicked()
            is LoginEvent.EmailChanged -> emailChanged(event.email)
            is LoginEvent.PasswordChanged -> passwordChanged(event.password)
            is LoginEvent.RepeatedPasswordChanged -> repeatedPasswordChanged(event.password)
            is LoginEvent.SubStateChanged -> subStateChanged(event.subState)
        }
    }

    private fun alreadyHaveAccountButtonClicked() = viewModelScope.launch {
        _sideEffect.send(LoginSideEffect.NavigateToLogin)
    }

    private fun createNewAccountButtonClicked() = viewModelScope.launch {
        _sideEffect.send(LoginSideEffect.NavigateToRegister)
    }

    private fun loginButtonClicked(
        email: String = _state.value.email,
        password: String = _state.value.password,
        subState: LoginSubState = _state.value.subState
    ) {
        if (!isValidationSuccessful()) return

        Log.d("TAGTAG", subState.name)

        viewModelScope.launch {
            login.execute(email, password, subState).onEach { result ->
                when (result) {
                    is Resource.Error -> showSnackbar(
                        result.error
                            ?: UiText.StringResource(com.example.ui_component.R.string.unexpected_error)
                    )
                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)
                    is Resource.Success -> result.data?.user?.uid?.let { userId ->
                        userSession.saveUserId(userId)
                        navigateToHome()
                    }
                }
            }.launchIn(this)
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

    private fun subStateChanged(subState: LoginSubState) {
        _state.value = _state.value.copy(subState = subState)
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
            if (!password.successful) {
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