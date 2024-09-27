package com.pks.shoppingapp.authentication.domain.repo

import com.pks.shoppingapp.authentication.domain.model.UserData
import com.pks.shoppingapp.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingapp.authentication.presentation.profile.UpdateUserDataState
import com.pks.shoppingapp.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepo {
    fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
    fun signInWithEmailAndPassword(email:String,password:String): Flow<ResultState<String>>
    fun getUserDataByUid(id:String): Flow<ResultState<ProfileScreenState>>
    fun updateUserData(userData: UserData): Flow<ResultState<UpdateUserDataState>>

}