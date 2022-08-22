package com.example.feature_login.domain.use_case

import com.example.feature_login.domain.repository.LoginRepository

class Register(
    private val repository: LoginRepository
) {

    fun execute(email: String, password: String) = repository.register(email, password)
}