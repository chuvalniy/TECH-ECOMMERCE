package com.example.feature_login.di

import com.example.feature_login.domain.use_case.*
import org.koin.dsl.module

val loginDomainModule = module {
    factory { Login(repository = get()) }

    factory { ValidateEmail() }

    factory { ValidatePassword() }

    factory { ValidateRepeatedPassword() }
}