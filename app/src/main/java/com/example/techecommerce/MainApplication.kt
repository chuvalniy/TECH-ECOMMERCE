package com.example.techecommerce

import android.app.Application
import com.example.feature_home.di.homeDataModule
import com.example.feature_home.di.homePresentationModule
import com.example.feature_search.di.searchDataModule
import com.example.feature_search.di.searchPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                mainModule,
                homeDataModule,
                homePresentationModule,
                searchDataModule,
                searchPresentationModule
            )
        }
    }
}