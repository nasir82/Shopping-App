package com.pks.shoppingapp.personalization.orders.domain.usecase

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.orders.domain.repo.OrderRepo
import com.pks.shoppingapp.personalization.orders.presentation.OrderState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(private  val orderRepo: OrderRepo) {
    suspend fun getOrders(): Flow<ResultState<OrderState>> {
        return orderRepo.getAOrders()
    }
}