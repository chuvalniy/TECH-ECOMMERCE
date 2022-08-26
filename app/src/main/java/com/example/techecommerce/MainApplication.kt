package com.example.techecommerce

import android.app.Application
import com.example.data_user_session.di.sharedPrefModule
import com.example.feature_cart.di.cartDataModule
import com.example.feature_cart.di.cartDomainModule
import com.example.feature_cart.di.cartViewModelModule
import com.example.feature_details.di.detailsDataModule
import com.example.feature_details.di.detailsDomainModule
import com.example.feature_details.di.detailsViewModelModule
import com.example.feature_favorites.di.favoritesDataModule
import com.example.feature_favorites.di.favoritesDomainModule
import com.example.feature_favorites.di.favoritesViewModelModule
import com.example.feature_home.di.homeDataModule
import com.example.feature_home.di.homeDomainModule
import com.example.feature_home.di.homeViewModelModule
import com.example.feature_login.di.loginDataModule
import com.example.feature_login.di.loginDomainModule
import com.example.feature_login.di.loginViewModelModule
import com.example.feature_profile.di.profileDataModule
import com.example.feature_profile.di.profileDomainModule
import com.example.feature_profile.di.profileViewModelModule
import com.example.feature_search.di.searchDataModule
import com.example.feature_search.di.searchDomainModule
import com.example.feature_search.di.searchViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                mainModule,
                sharedPrefModule,

                homeDataModule,
                homeDomainModule,
                homeViewModelModule,

                searchDataModule,
                searchDomainModule,
                searchViewModelModule,

                cartDataModule,
                cartDomainModule,
                cartViewModelModule,

                detailsDataModule,
                detailsDomainModule,
                detailsViewModelModule,

                loginDataModule,
                loginDomainModule,
                loginViewModelModule,

                profileDataModule,
                profileDomainModule,
                profileViewModelModule,

                favoritesDataModule,
                favoritesDomainModule,
                favoritesViewModelModule,
            )
        }
    }
}