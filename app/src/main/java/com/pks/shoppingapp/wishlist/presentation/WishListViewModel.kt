package com.pks.shoppingapp.wishlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.ProductState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import com.pks.shoppingapp.wishlist.domain.use_case.AddToWishListUseCase
import com.pks.shoppingapp.wishlist.domain.use_case.GetWishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WishListViewModel @Inject constructor(
    private val addToWishListUseCase: AddToWishListUseCase,
    private val getWishListUseCase: GetWishListUseCase
) : ViewModel() {
    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()
    private val _uploadWishlistState = MutableStateFlow(WishListUploadState())
    val uploadWishlistState = _uploadWishlistState.asStateFlow()

    fun getWishList() {
        viewModelScope.launch {
            getWishListUseCase.getWishList().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _productState.value = ProductState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _productState.value = ProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        _productState.value = ProductState(products = it.data.products)

                    }
                }
            }
        }
    }

    fun addToWishList(product: ProductModel) {
        viewModelScope.launch {
            addToWishListUseCase.addToWishList(product).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _uploadWishlistState.value = WishListUploadState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _uploadWishlistState.value = WishListUploadState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        var lst = ArrayList(_productState.value.products)
                        lst.add(product)
                        _productState.value = _productState.value.copy(
                            products = lst.toList()
                        )
                        _uploadWishlistState.value = WishListUploadState(isLoaded = true)
                    }
                }
            }
        }
    }


}