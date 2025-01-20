package com.pks.shoppingapp.cart.presentation

import com.pks.shoppingapp.cart.domain.model.CartModel

data class CartState(
    val isLoading:Boolean = false,
    val error:String = "",
    val products:List<CartModel> = emptyList()
)