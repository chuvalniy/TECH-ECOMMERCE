package com.example.techecommerce

import android.app.Application
import com.example.feature_home.di.homeDataModule
import com.example.feature_home.di.homeDomainModule
import com.example.feature_home.di.homePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                homeDataModule,
                homeDomainModule,
                homePresentationModule
            )
        }
    }
}