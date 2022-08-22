package com.example.feature_login.di

import com.example.feature_login.presentation.view_model.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginPresentationModule = module {

    viewModel {
        LoginViewModel(
            login = get(),
            register = get(),
            validateEmail = get(),
            validatePassword = get(),
            validateRepeatedPassword = get(),
            userSession = get()
        )
    }
}