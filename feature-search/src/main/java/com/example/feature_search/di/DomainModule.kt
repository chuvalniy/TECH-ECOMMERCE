package com.example.feature_search.di

import com.example.feature_search.domain.use_case.SearchData
import org.koin.dsl.module

val searchDomainModule = module {
    factory {
        SearchData(repository = get())
    }
}