package com.example.feature_details.di

import androidx.lifecycle.SavedStateHandle
import com.example.feature_details.presentation.view_model.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsPresentationModule = module {
    viewModel { (handle: SavedStateHandle) ->
        DetailsViewModel(
            detailsRepository = get(),
            cartRepository = get(),
            favoritesRepository = get(),
            userSession = get(),
            savedState = handle
        )
    }
}