package com.example.feature_home.di

import com.example.feature_home.domain.use_case.FetchHome
import org.koin.dsl.module

val homeDomainModule = module {

    factory {
        FetchHome(repository = get())
    }
}