package com.pks.shoppingapp.wishlist.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.wishlist.data.WishListRepoImp
import com.pks.shoppingapp.wishlist.domain.repo.WishListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class WishListModule {

    @Provides
    fun providesRepo(db:FirebaseFirestore,auth: FirebaseAuth):WishListRepo{
        return  WishListRepoImp(db,auth)
    }
}