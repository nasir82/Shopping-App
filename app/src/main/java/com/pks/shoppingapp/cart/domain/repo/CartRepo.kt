package com.pks.shoppingapp.cart.domain.repo

import com.pks.shoppingapp.cart.domain.model.CartModel
import com.pks.shoppingapp.cart.presentation.CartState
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import kotlinx.coroutines.flow.Flow

interface CartRepo {
    suspend fun getCarts(): Flow<ResultState<CartState>>
    suspend fun addToCart(cartModel: CartModel): Flow<ResultState<WishListUploadState>>
    suspend fun updateCart(cartModel: CartModel): Flow<ResultState<WishListUploadState>>
    suspend fun removeCart(id: String): Flow<ResultState<WishListUploadState>>
}