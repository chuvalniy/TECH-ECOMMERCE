package com.example.feature_cart.di

import com.example.feature_cart.domain.use_case.DeleteCart
import com.example.feature_cart.domain.use_case.DeleteSingleCart
import com.example.feature_cart.domain.use_case.FetchCart
import com.example.feature_cart.domain.use_case.InsertCart
import org.koin.dsl.module

val cartDomainModule = module {
    factory {
        DeleteCart(repository = get())
    }

    factory {
        DeleteSingleCart(repository = get())
    }

    factory {
        FetchCart(repository = get())
    }

    factory {
        InsertCart(repository = get())
    }
}