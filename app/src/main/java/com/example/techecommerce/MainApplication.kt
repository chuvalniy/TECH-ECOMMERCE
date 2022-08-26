package com.example.techecommerce

import android.app.Application
import com.example.data_user_session.di.sharedPrefModule
import com.example.feature_cart.di.cartDataModule
import com.example.feature_cart.di.cartPresentationModule
import com.example.feature_details.di.detailsDataModule
import com.example.feature_details.di.detailsDomainModule
import com.example.feature_details.di.detailsViewModelModule
import com.example.feature_favorites.di.favoritesDataModule
import com.example.feature_favorites.di.favoritesViewModelModule
import com.example.feature_home.di.homeDataModule
import com.example.feature_home.di.homePresentationModule
import com.example.feature_login.di.loginDataModule
import com.example.feature_login.di.loginDomainModule
import com.example.feature_login.di.loginPresentationModule
import com.example.feature_profile.di.profileDataModule
import com.example.feature_profile.di.profilePresentationModule
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
                searchPresentationModule,
                cartDataModule,
                cartPresentationModule,
                detailsDataModule,
                detailsDomainModule,
                detailsViewModelModule,
                loginDataModule,
                loginDomainModule,
                loginPresentationModule,
                sharedPrefModule,
                profileDataModule,
                profilePresentationModule,
                favoritesDataModule,
                favoritesViewModelModule
            )
        }
    }
}