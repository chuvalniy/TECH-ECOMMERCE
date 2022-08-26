package com.example.data_user_session.di

import com.example.data_user_session.data.UserPreferences
import com.example.data_user_session.data.UserPreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPrefModule = module {
    single<UserPreferences> {
        UserPreferencesImpl(androidContext())
    }
}