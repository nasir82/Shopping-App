package com.pks.shoppingapp.authentication.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.authentication.data.AuthenticationRepoImp
import com.pks.shoppingapp.authentication.domain.repo.AuthenticationRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    fun providesAuthenticationRepo( db:FirebaseFirestore,auth:FirebaseAuth):AuthenticationRepo{
        return AuthenticationRepoImp(db,auth)
    }
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return  FirebaseAuth.getInstance()
    }
}