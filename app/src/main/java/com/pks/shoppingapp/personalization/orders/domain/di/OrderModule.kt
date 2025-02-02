package com.pks.shoppingapp.personalization.orders.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.personalization.orders.data.OrderRepoImp
import com.pks.shoppingapp.personalization.orders.domain.repo.OrderRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class OrderModule {
    @Provides
    fun provideOrderRepo(db: FirebaseFirestore, auth: FirebaseAuth): OrderRepo {
        return OrderRepoImp(db,auth)
    }
}