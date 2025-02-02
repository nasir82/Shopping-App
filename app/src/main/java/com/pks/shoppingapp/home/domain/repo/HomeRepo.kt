package com.pks.shoppingapp.home.domain.repo

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.home.presentation.CategoryState
import com.pks.shoppingapp.home.presentation.ProductState
import kotlinx.coroutines.flow.Flow

interface HomeRepo {
    suspend fun getCategories(): Flow<ResultState<CategoryState>>
    suspend fun getProducts(): Flow<ResultState<ProductState>>
}


