package com.pks.shoppingapp.personalization.orders.domain.usecase

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.orders.domain.model.OrderModel
import com.pks.shoppingapp.personalization.orders.domain.repo.OrderRepo
import com.pks.shoppingapp.personalization.orders.presentation.OrderState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddOrderUseCase @Inject constructor(private  val orderRepo: OrderRepo) {
    suspend fun addOrder(order: OrderModel): Flow<ResultState<OrderState>> {
        return orderRepo.addOrder(order)
    }
}