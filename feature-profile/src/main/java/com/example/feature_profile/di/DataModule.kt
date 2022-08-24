package com.example.feature_profile.di

import androidx.room.Room
import com.example.core.utils.JsonParser
import com.example.feature_profile.data.local.ProfileDatabase
import com.example.feature_profile.data.local.converters.Converters
import com.example.feature_profile.data.remote.ProfileFirestore
import com.example.feature_profile.data.remote.ProfileFirestoreImpl
import com.example.feature_profile.data.repository.ProfileRepositoryImpl
import com.example.feature_profile.domain.repository.ProfileRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val profileDataModule = module {
    single<ProfileRepository> {
        ProfileRepositoryImpl(db = get(), api = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            ProfileDatabase::class.java,
            ProfileDatabase.DATABASE_NAME
        )
            .addTypeConverter(Converters(JsonParser.GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }

    single<ProfileFirestore> {
        ProfileFirestoreImpl(firestore = get())
    }

    single {
        FirebaseFirestore.getInstance()
    }
}