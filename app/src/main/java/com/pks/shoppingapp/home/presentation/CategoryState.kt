package com.pks.shoppingapp.home.presentation

import com.pks.shoppingapp.home.domain.model.CategoryModel

data class CategoryState(
    val isLoading:Boolean = false,
    val error:String = "",
    val categories:List<CategoryModel> = emptyList()
)