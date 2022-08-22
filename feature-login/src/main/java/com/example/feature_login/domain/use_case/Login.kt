package com.example.feature_login.domain.use_case

import com.example.feature_login.domain.repository.LoginRepository
import com.example.feature_login.presentation.model.LoginSubState

class Login(
    private val repository: LoginRepository
) {

    fun execute(email: String, password: String, subState: LoginSubState) =
        repository.login(email, password, subState)
}