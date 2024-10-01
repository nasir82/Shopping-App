package com.pks.shoppingapp.wishlist.domain.use_case

import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import com.pks.shoppingapp.wishlist.domain.repo.WishListRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToWishListUseCase @Inject constructor(private  val wishListRepo: WishListRepo) {
    suspend fun addToWishList(product:ProductModel): Flow<ResultState<WishListUploadState>> {
        return wishListRepo.addToWishList(productModel = product)
    }
}