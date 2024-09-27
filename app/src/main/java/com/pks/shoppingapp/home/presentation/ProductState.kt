package com.pks.shoppingapp.home.presentation

import com.pks.shoppingapp.home.domain.model.ProductModel

data class ProductState(
    val isLoading:Boolean = false,
    val error:String = "",
    val products:List<ProductModel> = emptyList()
)