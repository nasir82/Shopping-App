package com.pks.shoppingapp.cart.domain.use_case

import com.pks.shoppingapp.cart.domain.model.CartModel
import com.pks.shoppingapp.cart.domain.repo.CartRepo
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.wishlist.data.WishListUploadState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(private val cartRepo: CartRepo) {

    suspend fun updateCart(cartModel: CartModel): Flow<ResultState<WishListUploadState>> {
        return cartRepo.updateCart(cartModel = cartModel)
    }

}