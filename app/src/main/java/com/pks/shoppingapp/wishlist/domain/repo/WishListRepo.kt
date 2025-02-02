package com.pks.shoppingapp.wishlist.domain.repo

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.ProductState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import kotlinx.coroutines.flow.Flow

interface WishListRepo {
    suspend fun wishList(): Flow<ResultState<ProductState>>
    suspend fun addToWishList(productModel: ProductModel): Flow<ResultState<WishListUploadState>>
}