package com.example.feature_details.di

import androidx.lifecycle.SavedStateHandle
import com.example.feature_details.presentation.view_model.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsViewModelModule = module {
    viewModel { (handle: SavedStateHandle) ->
        DetailsViewModel(
            cartRepository = get(),
            favoritesRepository = get(),
            userSession = get(),
            fetchData = get(),
            savedState = handle
        )
    }
}