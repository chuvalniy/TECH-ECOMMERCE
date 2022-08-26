package com.example.feature_cart.di

import com.example.feature_cart.presentation.cart.view_model.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartViewModelModule = module {

    viewModel {
        CartViewModel(
            fetchCart = get(),
            deleteCart = get(),
            deleteSingleCart = get(),
            insertCart = get(),
            userPref = get()
        )
    }
}