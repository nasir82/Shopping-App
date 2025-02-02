package com.pks.shoppingapp.personalization.orders.domain.repo

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.orders.domain.model.OrderModel
import com.pks.shoppingapp.personalization.orders.presentation.OrderState
import kotlinx.coroutines.flow.Flow


interface OrderRepo {
    suspend fun getAOrders(): Flow<ResultState<OrderState>>
    suspend fun addOrder(order: OrderModel): Flow<ResultState<OrderState>>
}