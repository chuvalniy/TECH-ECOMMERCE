package com.example.feature_details.di

import androidx.room.Room
import com.example.feature_details.data.local.DetailsDatabase
import com.example.feature_details.data.remote.DetailsFirestore
import com.example.feature_details.data.remote.DetailsFirestoreImpl
import com.example.feature_details.data.repository.DetailsRepositoryImpl
import com.example.feature_details.domain.repository.DetailsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val detailsDataModule = module {
    single<DetailsRepository> {
        DetailsRepositoryImpl(db = get(), api = get())
    }

    single<DetailsFirestore> {
        DetailsFirestoreImpl()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            DetailsDatabase::class.java,
            DetailsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}