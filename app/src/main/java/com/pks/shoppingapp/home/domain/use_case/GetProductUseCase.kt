package com.pks.shoppingapp.home.domain.use_case

import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.home.domain.repo.HomeRepo
import com.pks.shoppingapp.home.presentation.ProductState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private  val homeRepo: HomeRepo) {
    suspend fun getProducts(): Flow<ResultState<ProductState>> {
        return homeRepo.getProducts()
    }
}