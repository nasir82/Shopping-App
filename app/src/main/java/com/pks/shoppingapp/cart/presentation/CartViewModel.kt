package com.pks.shoppingapp.cart.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.cart.domain.model.CartModel
import com.pks.shoppingapp.cart.domain.use_case.AddCartUseCase
import com.pks.shoppingapp.cart.domain.use_case.GetCartUseCase
import com.pks.shoppingapp.cart.domain.use_case.RemoveCartUserCase
import com.pks.shoppingapp.cart.domain.use_case.UpdateCartUseCase
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.domain.model.ProductVariationsModel
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val addToAddCartUseCase: AddCartUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val removeCartUserCase: RemoveCartUserCase
) : ViewModel() {
    private val _productState = MutableStateFlow(CartState())
    val productState = _productState.asStateFlow()
    private val _uploadWishlistState = MutableStateFlow(WishListUploadState())
    val uploadWishlistState = _uploadWishlistState.asStateFlow()
    private val _totalCost = MutableStateFlow(0.0)
    val totalCost = _totalCost.asStateFlow()

    fun getCarts() {
        viewModelScope.launch {
            _productState.value = CartState(isLoading = true)
            getCartUseCase.getCarts().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _productState.value = CartState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _productState.value = CartState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        _productState.value = CartState(products = it.data.products)
                        getTotalCost()

                    }
                }
            }
        }
    }

    fun getTotalCost(){
        var total = 0.0

        val list = productState.value.products
        list.forEach {
            total += it.quantity * it.price
        }

        _totalCost.value = total

    }

    fun getProductQuantityInCart(productId: String): Int {
        val productQuantity = productState.value.products.firstOrNull {
            it.id == productId
        }

        return productQuantity?.quantity ?: 0
    }

    fun convertIntoCartItem(
        product: ProductModel,
        quantity: Int,
        variation: ProductVariationsModel
    ): CartModel {

        /**
         * image
         * variation id
         * price
         * attributes
         * these are dependent weather the product is single or variable so first of all fixed out these and
         * then initiate the instance of cartmodel
         *
         */

        val productType = product.productType == "variable"
        val price = if (productType) {
            if (variation.salePrice > 0) variation.salePrice else variation.price
        } else {
            if (product.salePrice > 0) product.salePrice else product.price
        }
        val image = if (productType) variation.image else product.thumbnail
        val cartModel = CartModel(
            id = UUID.randomUUID().toString(),
            productId = product.id,
            variationId = variation.id,
            image = image,
            title = product.title,
            brandName = product.brand.name,
            price = price,
            selectedVariationAttributes = variation.attributeValues,
            quantity = quantity
        )

        Log.d(
            "cartFormat",
            cartModel.toString() + product.productType.toString() + productType.toString()
        )

        return cartModel
    }

    fun addOneToCart(id: String) {

        viewModelScope.launch {
            val list = productState.value.products.toMutableList()
            var newProduct = list.firstOrNull {
                it.id == id
            }
            if (newProduct != null) {

                newProduct = newProduct.copy(
                    quantity = newProduct.quantity + 1
                )

                updateCartUseCase.updateCart(newProduct!!).collectLatest {
                    when (it) {
                        is ResultState.Error -> {
                            _uploadWishlistState.value = WishListUploadState(error = it.message)
                        }

                        ResultState.Loading -> {
                            _uploadWishlistState.value = WishListUploadState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            val list = productState.value.products.toMutableList()

                            list.forEachIndexed { index, cartModel ->

                                if (list[index].id == id) {
                                    list[index] = list[index].copy(
                                        quantity = list[index].quantity + 1
                                    )
                                }
                            }
                            _productState.value = CartState(products = list)
                            getTotalCost()
                            _uploadWishlistState.value = WishListUploadState(isLoaded = true)
                        }
                    }

                }

            }


        }
    }

    fun removeOneToCart(id: String) {
        // Create a copy of the current products list

        viewModelScope.launch {


            val list = productState.value.products.toMutableList()

            // Iterate safely
            for (i in list.indices) {
                val cartModel = list[i]
                if (cartModel.id == id) {
                    if (cartModel.quantity == 1) {
                        // Remove the item when quantity is 1

                        removeCartUserCase.removeCart(id).collectLatest {
                            when (it) {
                                is ResultState.Error -> {
                                    _uploadWishlistState.value = WishListUploadState(error = it.message)
                                }

                                ResultState.Loading -> {
                                    _uploadWishlistState.value = WishListUploadState(isLoading = true)
                                }

                                is ResultState.Success -> {
                                    list.removeAt(i)
                                    _productState.value = CartState(products = list)
                                    getTotalCost()
                                }
                            }

                        }

                    } else {
                        // Update the item with decreased quantity
                        var newProduct = list.firstOrNull {
                            it.id == id
                        }
                        if (newProduct != null) {

                            newProduct = newProduct.copy(
                                quantity = newProduct.quantity - 1
                            )

                            updateCartUseCase.updateCart(newProduct!!).collectLatest {
                                when (it) {
                                    is ResultState.Error -> {
                                        _uploadWishlistState.value = WishListUploadState(error = it.message)
                                    }

                                    ResultState.Loading -> {
                                        _uploadWishlistState.value = WishListUploadState(isLoading = true)
                                    }

                                    is ResultState.Success -> {
                                        list[i] = cartModel.copy(quantity = cartModel.quantity - 1)
                                        _productState.value = CartState(products = list)
                                        getTotalCost()
                                    }
                                }

                            }

                        }


                    }
                    break // Exit loop once the item is processed
                }
            }

            // Update the state
            _productState.value = CartState(products = list)
        }
    }


    fun addToCart(product: CartModel) {
        viewModelScope.launch {

            /**
             * try to find out that whether this product available in the cart already
             * if it is available then update the product quantity
             * else add to the cart list
             */
            val list = productState.value.products
            val isAvailable = list.firstOrNull {
                it.variationId == product.variationId && it.selectedVariationAttributes == product.selectedVariationAttributes && product.id==it.productId
            }
            var newProduct = product
            if (isAvailable != null) {
                newProduct = isAvailable.copy(
                    quantity = isAvailable.quantity + 1
                )

                updateCartUseCase.updateCart(newProduct).collectLatest {
                    when (it) {
                        is ResultState.Error -> {
                            _uploadWishlistState.value = WishListUploadState(error = it.message)
                        }

                        ResultState.Loading -> {
                            _uploadWishlistState.value = WishListUploadState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            val list = productState.value.products.toMutableList()

                            list.forEachIndexed { index, cartModel ->

                                if (list[index].id == newProduct.id) {
                                    list[index] = list[index].copy(
                                        quantity = list[index].quantity + 1
                                    )
                                }
                            }
                            _productState.value = CartState(products = list)
                            getTotalCost()
                            _uploadWishlistState.value = WishListUploadState(isLoaded = true)
                        }
                    }

                }

            } else {
                addToAddCartUseCase.addToCart(newProduct).collectLatest {
                    when (it) {
                        is ResultState.Error -> {
                            _uploadWishlistState.value = WishListUploadState(error = it.message)
                        }

                        ResultState.Loading -> {
                            _uploadWishlistState.value = WishListUploadState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            val list = productState.value.products.toMutableList()
                            list.add(product)
                            _productState.value = CartState(products = list)
                            _uploadWishlistState.value = WishListUploadState(isLoaded = true)
                        }
                    }
                }
            }
        }
    }


}