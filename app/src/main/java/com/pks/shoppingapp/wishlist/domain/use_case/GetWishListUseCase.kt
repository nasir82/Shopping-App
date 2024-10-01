package com.pks.shoppingapp.wishlist.domain.use_case

import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.presentation.ProductState
import com.pks.shoppingapp.wishlist.domain.repo.WishListRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWishListUseCase @Inject constructor(private  val wishListRepo: WishListRepo) {
    suspend fun getWishList(): Flow<ResultState<ProductState>> {
        return wishListRepo.wishList()
    }
}