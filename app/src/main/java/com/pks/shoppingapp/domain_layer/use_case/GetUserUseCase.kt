package com.pks.shoppingapp.domain_layer.use_case

import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.domain_layer.model.UserData
import com.pks.shoppingapp.domain_layer.repo.ShoppingRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo:ShoppingRepo) {

    fun getUserWithuid(uid:String):Flow<ResultState<UserData>>{
        return repo.getUserDataByUid(uid)
    }
}