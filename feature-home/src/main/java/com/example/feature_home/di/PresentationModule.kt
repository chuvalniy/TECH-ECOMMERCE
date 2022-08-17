package com.example.feature_home.di

import com.example.feature_home.presentation.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homePresentationModule = module {
    viewModel {
        HomeViewModel(repository = get())
    }
}