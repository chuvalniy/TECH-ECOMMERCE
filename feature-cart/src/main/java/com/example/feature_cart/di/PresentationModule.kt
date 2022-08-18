package com.example.feature_cart.di

import com.example.feature_cart.presentation.view_model.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartPresentationModule = module {

    viewModel {
        CartViewModel(repository = get())
    }
}