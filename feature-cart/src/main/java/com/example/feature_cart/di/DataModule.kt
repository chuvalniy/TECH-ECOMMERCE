package com.example.feature_cart.di

import androidx.room.Room
import com.example.feature_cart.data.local.CartDatabase
import com.example.feature_cart.data.remote.CartFirestore
import com.example.feature_cart.data.remote.CartFirestoreImpl
import com.example.feature_cart.data.repository.CartRepositoryImpl
import com.example.feature_cart.domain.repository.CartRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cartDataModule = module {
    single<CartRepository> {
        CartRepositoryImpl(db = get(), api = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            CartDatabase::class.java,
            CartDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<CartFirestore> {
        CartFirestoreImpl()
    }
}