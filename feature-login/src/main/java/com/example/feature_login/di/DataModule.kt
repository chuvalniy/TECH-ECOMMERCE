package com.example.feature_login.di

import com.example.feature_login.data.api.LoginApi
import com.example.feature_login.data.repository.LoginRepositoryImpl
import com.example.feature_login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val loginDataModule = module {

    single<LoginApi> {
        LoginApi.Base(firebaseAuth = get())
    }

    single {
        FirebaseAuth.getInstance()
    }

    single<LoginRepository> {
        LoginRepositoryImpl(api = get())
    }
}