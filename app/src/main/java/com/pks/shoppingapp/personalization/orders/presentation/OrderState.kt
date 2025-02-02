package com.pks.shoppingapp.personalization.orders.presentation

import com.pks.shoppingapp.personalization.orders.domain.model.OrderModel

data  class OrderState(
    val isLoading: Boolean = false,
    val errorMessage:String? = null,
    val orders: List<OrderModel> = emptyList()
)