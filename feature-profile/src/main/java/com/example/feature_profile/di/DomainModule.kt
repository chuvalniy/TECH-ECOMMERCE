package com.example.feature_profile.di

import com.example.feature_profile.domain.use_case.FetchProfile
import org.koin.dsl.module

val profileDomainModule = module {

    factory {
        FetchProfile(repository = get())
    }
}