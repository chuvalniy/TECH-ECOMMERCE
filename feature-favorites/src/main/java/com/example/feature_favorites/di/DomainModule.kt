package com.example.feature_favorites.di

import com.example.feature_favorites.domain.use_case.DeleteFavorite
import com.example.feature_favorites.domain.use_case.FetchFavorites
import com.example.feature_favorites.domain.use_case.InsertFavorite
import org.koin.dsl.module

val favoritesDomainModule = module {
    factory {
        InsertFavorite(repository = get())
    }

    factory {
        DeleteFavorite(repository = get())
    }

    factory {
        FetchFavorites(repository = get())
    }
}