package com.example.feature_home.di

import androidx.room.Room
import com.example.feature_home.data.local.HomeDatabase
import com.example.feature_home.data.remote.HomeFirestore
import com.example.feature_home.data.remote.HomeFirestoreImpl
import com.example.feature_home.data.repository.HomeRepositoryImpl
import com.example.feature_home.domain.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val homeDataModule = module {

    single<HomeRepository> {
        HomeRepositoryImpl(db = get(), api = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            HomeDatabase::class.java,
            HomeDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<HomeFirestore> {
        HomeFirestoreImpl(firestore = get())
    }

    single {
        FirebaseFirestore.getInstance()
    }
}