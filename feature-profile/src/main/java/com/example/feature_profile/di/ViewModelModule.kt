package com.example.feature_profile.di

import androidx.lifecycle.SavedStateHandle
import com.example.feature_profile.presentation.edit_profile.view_model.EditProfileViewModel
import com.example.feature_profile.presentation.profile.view_model.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileViewModelModule = module {
    viewModel {
        ProfileViewModel(
            fetchProfile = get(),
            userPref = get()
        )
    }

    viewModel { (handle: SavedStateHandle) ->
        EditProfileViewModel(
            savedState = handle,
            updateProfile = get(),
            userPref = get()
        )
    }
}