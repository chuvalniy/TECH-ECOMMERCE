package com.example.data_user_session.di

import com.example.data_user_session.data.UserSession
import com.example.data_user_session.data.UserSessionImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val userSessionDataModule = module {
    single<UserSession> {
        UserSessionImpl(androidContext())
    }
}