package com.example.feature_favorites.di

import com.example.feature_favorites.presentation.view_model.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesPresentationModule = module {

    viewModel {
        FavoritesViewModel(
            repository = get(),
            userSession = get()
        )
    }
}