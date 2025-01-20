package com.pks.shoppingapp.wishlist.utils

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.home.presentation.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(application: Application) : ViewModel() {

    private val dataStoreHelper:DataStoreHelper = DataStoreHelper.getInstance(application)


    /**
     * this should be improve using an singleton object later
     *
     */

    private val _favorites = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val favorites = _favorites.asStateFlow()
    companion object{
        private val _favorites2 = MutableStateFlow<Map<String, Boolean>>(emptyMap())
        val favorites = _favorites2.asStateFlow()
    }

    init {
        fetchFavorites()
        Log.d("init block is going to executes","executed")
    }


    private fun fetchFavorites() {
        viewModelScope.launch {
            dataStoreHelper.fetchFavorites().collect { storedFavorites ->
                _favorites.value = storedFavorites
                HomeViewModel.fetchFavouriteProductState(_favorites.value)
            }

        }

    }

    fun resetFav(){
        _favorites.value = emptyMap()
    }
    fun toggleFavorite(productId: String) {
        viewModelScope.launch {
            val currentFavorites = _favorites.value.toMutableMap()
            val isFavorite = currentFavorites[productId] ?: false
            if(isFavorite){
                currentFavorites.remove(productId)
                dataStoreHelper.removeFavorite(productId)
                _favorites.value = currentFavorites
            }else{
                currentFavorites[productId] = !isFavorite
                _favorites.value = currentFavorites
                dataStoreHelper.addFavorite(productId, !isFavorite)
            }

        }
    }

    fun removeFavorite(productId: String) {
        viewModelScope.launch {
            val currentFavorites = _favorites.value.toMutableMap()
            currentFavorites.remove(productId)
            _favorites.value = currentFavorites
            dataStoreHelper.removeFavorite(productId)
        }
    }
}



//companion object {
//    @Volatile
//    private var instance: DataStoreViewModel? = null
//
//    // Singleton initialization method
//    fun getInstance(application: Application): DataStoreViewModel {
//        return instance ?: synchronized(this) {
//            instance ?: DataStoreViewModel(application).also { instance = it }
//        }
//    }
