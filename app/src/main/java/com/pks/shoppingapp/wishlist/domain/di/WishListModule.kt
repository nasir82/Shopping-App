package com.pks.shoppingapp.wishlist.domain.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingapp.wishlist.data.WishListRepoImp
import com.pks.shoppingapp.wishlist.domain.repo.WishListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WishListModule {

    @Provides
    @Singleton
    fun providesRepo(db:FirebaseFirestore,auth: FirebaseAuth):WishListRepo{
        return  WishListRepoImp(db,auth)
    }
    @Provides
    @Singleton
    fun providesCotext(@ApplicationContext context: Context):Context{
        return  context
    }
}