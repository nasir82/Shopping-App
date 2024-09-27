package com.pks.shoppingapp.authentication.domain.usecase

import com.pks.shoppingapp.authentication.domain.model.UserData
import com.pks.shoppingapp.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingapp.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repo: AuthenticationRepo) {
    fun createUser(userData: UserData): Flow<ResultState<String>> {
        return repo.registerUserWithEmailAndPassword(userData)
    }
}