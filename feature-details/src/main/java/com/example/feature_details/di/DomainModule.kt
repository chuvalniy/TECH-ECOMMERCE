package com.example.feature_details.di

import com.example.feature_details.domain.use_case.FetchData
import org.koin.dsl.module

val detailsDomainModule = module {
    factory {
        FetchData(
            detailsRepository = get(),
            favoritesRepository = get()
        )
    }
}