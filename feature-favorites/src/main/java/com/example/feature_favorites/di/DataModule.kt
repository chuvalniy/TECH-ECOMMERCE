package com.example.feature_favorites.di

import androidx.room.Room
import com.example.feature_favorites.data.local.FavoritesDatabase
import com.example.feature_favorites.data.remote.FavoritesFirestore
import com.example.feature_favorites.data.remote.FavoritesFirestoreImpl
import com.example.feature_favorites.data.repository.FavoritesRepositoryImpl
import com.example.feature_favorites.domain.repository.FavoritesRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val favoritesDataModule = module {
    single <FavoritesRepository> {
        FavoritesRepositoryImpl(db = get(), api = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            FavoritesDatabase::class.java,
            FavoritesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single <FavoritesFirestore> {
        FavoritesFirestoreImpl(firestore = get())
    }

    single {
        FirebaseFirestore.getInstance()
    }
}