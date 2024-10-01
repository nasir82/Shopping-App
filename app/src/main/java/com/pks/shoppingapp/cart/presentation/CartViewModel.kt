package com.pks.shoppingapp.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.cart.domain.use_case.AddCartUseCase
import com.pks.shoppingapp.cart.domain.use_case.GetCartUseCase
import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.ProductState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val addToAddCartUseCase: AddCartUseCase,
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {
    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()
    private val _uploadWishlistState = MutableStateFlow(WishListUploadState())
    val uploadWishlistState = _uploadWishlistState.asStateFlow()

    fun getCarts() {
        viewModelScope.launch {
            _productState.value = ProductState(isLoading = true)
            delay(10000)
            getCartUseCase.getCarts().collectLatest {
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

    fun addToCart(product: ProductModel) {
        viewModelScope.launch {
            addToAddCartUseCase.addToCart(product).collectLatest {
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