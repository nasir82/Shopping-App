package com.pks.shoppingapp.domain_layer.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.data.repo.ShoppingRepoImp
import com.pks.shoppingapp.domain_layer.repo.ShoppingRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule{
    @Provides
    fun provideRepo(auth:FirebaseAuth,firestore: FirebaseFirestore):ShoppingRepo{
        return  ShoppingRepoImp(auth,firestore)
    }
}