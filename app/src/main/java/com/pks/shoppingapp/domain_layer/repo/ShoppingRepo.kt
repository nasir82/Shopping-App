package com.pks.shoppingapp.domain_layer.repo

import com.pks.shoppingapp.authentication.domain.model.UserData
import com.pks.shoppingapp.common.ResultState
import kotlinx.coroutines.flow.Flow

interface ShoppingRepo {
     fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
     fun signInWithEmailAndPassword(email:String,password:String): Flow<ResultState<String>>
     fun getUserDataByUid(id:String): Flow<ResultState<UserData>>

}