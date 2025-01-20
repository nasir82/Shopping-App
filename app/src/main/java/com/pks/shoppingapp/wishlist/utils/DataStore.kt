package com.pks.shoppingapp.wishlist.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreHelper private constructor(private val dataStore: DataStore<Preferences>, val currentUserId: String) {

    companion object {
        private val dataStoreMap = mutableMapOf<String, DataStore<Preferences>>()

        // Singleton initialization method
        fun getInstance(context: Context): DataStoreHelper {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "default"

            // Check if a DataStore instance already exists for this userId
            val dataStore = dataStoreMap[userId] ?: synchronized(this) {
                // Re-check within synchronized block to prevent race conditions
                dataStoreMap[userId] ?: PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(userId)
                }.also { newDataStore ->
                    dataStoreMap[userId] = newDataStore
                }
            }

            return DataStoreHelper(dataStore,FirebaseAuth.getInstance().currentUser!!.uid)
        }

        // Clear DataStore for a specific user (e.g., on logout)
        fun clearInstance(userId: String) {
            synchronized(this) {
                dataStoreMap.remove(userId)
                Log.d("DataStoreHelper", "DataStore instance cleared for user: $userId")
            }
        }

        // Clear all DataStore instances (e.g., on app exit)
        fun clearAllInstances() {
            synchronized(this) {
                dataStoreMap.clear()
                Log.d("DataStoreHelper", "All DataStore instances cleared.")
            }
        }
    }

    // Save a map of favorites
    suspend fun saveFavorites(favorites: Map<String, Boolean>) {
        dataStore.edit { preferences ->
            favorites.forEach { (productId, isFavorite) ->
                preferences[booleanPreferencesKey(productId)] = isFavorite
            }
        }
    }

    // Fetch all favorites
    fun fetchFavorites(): Flow<Map<String, Boolean>> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences.asMap().mapNotNull { entry ->
                    val key = entry.key.name
                    val value = entry.value as? Boolean
                    Log.d("DataStoreHelper", "Inside collect: $value")
                    if (value != null) key to value else null
                }.toMap()
            }
    }

    // Add a single favorite
    suspend fun addFavorite(productId: String, isFavorite: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(productId)] = isFavorite
        }
    }

    // Remove a single favorite
    suspend fun removeFavorite(productId: String) {
        dataStore.edit { preferences ->
            preferences.remove(booleanPreferencesKey(productId))
        }
    }
}
