package com.pks.shoppingapp.cart.domain.repo

import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.ProductState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import kotlinx.coroutines.flow.Flow

interface CartRepo {
    suspend fun getCarts(): Flow<ResultState<ProductState>>
    suspend fun addToCart(productModel: ProductModel): Flow<ResultState<WishListUploadState>>
}