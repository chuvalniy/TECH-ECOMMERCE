package com.example.feature_details.di

import com.example.feature_details.domain.use_case.FetchDetails
import org.koin.dsl.module

val detailsDomainModule = module {
    factory {
        FetchDetails(
            detailsRepository = get(),
            favoritesRepository = get()
        )
    }
}