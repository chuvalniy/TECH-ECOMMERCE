package com.example.feature_home.di

import com.example.feature_home.domain.use_case.FetchData
import org.koin.dsl.module

val homeDomainModule = module {

    factory {
        FetchData(repository = get())
    }
}