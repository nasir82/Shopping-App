package com.pks.shoppingapp.wishlist.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private var dynamicDataStoreName: String = "default"
private val Context.dynamicDataStore: DataStore<Preferences> by preferencesDataStore(name = dynamicDataStoreName)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private var userSpecificDataStore: DataStore<Preferences>? = null

    @Provides
    @Singleton
    fun provideDataStore(context: Context): DataStore<Preferences> {
        return userSpecificDataStore ?: throw IllegalStateException(
            "DataStore has not been initialized. Call initializeDataStore first."
        )
    }

//    @Provides
//    @Singleton
//    fun provideDataStoreHelper(context: Context,dataStore: DataStore<Preferences>): DataStoreHelper {
//        return DataStoreHelper(context)
//    }

    fun initializeDataStore(context: Context, uid: String): DataStore<Preferences> {
        dynamicDataStoreName = uid
        Log.d("on init print", "initialized with $uid")
        if (userSpecificDataStore == null) {
            userSpecificDataStore = context.dynamicDataStore
        }
        return userSpecificDataStore!!
    }

    fun clearDataStore() {
        userSpecificDataStore = null
    }
}