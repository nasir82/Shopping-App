package com.pks.shoppingapp.core.domain.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun providesDataRepository(): FirebaseFirestore {
        return  FirebaseFirestore.getInstance()
    }
}