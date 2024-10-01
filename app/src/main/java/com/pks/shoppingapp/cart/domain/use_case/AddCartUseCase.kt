package com.pks.shoppingapp.cart.domain.use_case

import com.pks.shoppingapp.cart.domain.repo.CartRepo
import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCartUseCase @Inject constructor(private  val cartRepo: CartRepo) {
    suspend fun addToCart(product: ProductModel): Flow<ResultState<WishListUploadState>> {
        return cartRepo.addToCart(productModel = product)
    }
}