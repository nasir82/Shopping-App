package com.pks.shoppingapp.domain_layer.repo

import com.pks.shoppingapp.common.ResultState
import com.pks.shoppingapp.domain_layer.model.UserData
import kotlinx.coroutines.flow.Flow

interface ShoppingRepo {
     fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
     fun signInWithEmailAndPassword(email:String,password:String): Flow<ResultState<String>>
     fun getUserDataByUid(id:String): Flow<ResultState<UserData>>

}