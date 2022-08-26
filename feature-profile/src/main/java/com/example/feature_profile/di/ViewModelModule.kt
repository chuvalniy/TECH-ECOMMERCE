package com.example.feature_profile.di

import com.example.feature_profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileViewModelModule = module {
    viewModel {
        ProfileViewModel(
            fetchProfile = get(),
            userPref = get()
        )
    }
}