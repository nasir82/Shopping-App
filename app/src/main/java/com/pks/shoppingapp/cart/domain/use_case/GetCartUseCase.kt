package com.pks.shoppingapp.cart.domain.use_case

import com.pks.shoppingapp.cart.domain.repo.CartRepo
import com.pks.shoppingapp.cart.presentation.CartState
import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.presentation.ProductState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private  val cartRepo: CartRepo) {
    suspend fun getCarts(): Flow<ResultState<CartState>> {
        return cartRepo.getCarts()
    }
}