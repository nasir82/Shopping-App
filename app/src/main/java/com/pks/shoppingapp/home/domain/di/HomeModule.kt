package com.pks.shoppingapp.home.domain.di

import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.home.data.HomeRepoImp
import com.pks.shoppingapp.home.domain.repo.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun providesRepo(db:FirebaseFirestore):HomeRepo{
        return HomeRepoImp(db)
    }
}