package com.pks.shoppingapp.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesDataRepository():FirebaseFirestore{
        return  FirebaseFirestore.getInstance()
    }
    @Provides
    fun providesFirebaseAuth():FirebaseAuth{
        return  FirebaseAuth.getInstance()
    }
}