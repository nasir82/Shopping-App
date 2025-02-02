package com.pks.shoppingapp.home.domain.use_case

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.home.domain.repo.HomeRepo
import com.pks.shoppingapp.home.presentation.CategoryState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private  val homeRepo: HomeRepo) {
    suspend fun getCategories(): Flow<ResultState<CategoryState>> {
        return homeRepo.getCategories()
    }
}