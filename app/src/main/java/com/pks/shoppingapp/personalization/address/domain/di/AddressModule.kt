package com.pks.shoppingapp.personalization.address.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.personalization.address.data.AddressRepoImpl
import com.pks.shoppingapp.personalization.address.domain.repo.AddressRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AddressModule {
    @Provides
    fun provideAddressRepo(db:FirebaseFirestore,auth:FirebaseAuth):AddressRepo{
        return AddressRepoImpl(db,auth)
    }
}