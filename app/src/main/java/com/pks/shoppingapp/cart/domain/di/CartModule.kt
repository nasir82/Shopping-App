package com.pks.shoppingapp.cart.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.cart.data.CartRepoImp
import com.pks.shoppingapp.cart.domain.repo.CartRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CartModule {
    @Provides
    fun providesRepo(db: FirebaseFirestore, auth: FirebaseAuth): CartRepo {
        return  CartRepoImp(db,auth)
    }
}