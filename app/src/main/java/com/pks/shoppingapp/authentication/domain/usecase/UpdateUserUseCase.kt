package com.pks.shoppingapp.authentication.domain.usecase

import com.pks.shoppingapp.authentication.domain.model.UserData
import com.pks.shoppingapp.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingapp.authentication.presentation.profile.UpdateUserDataState
import com.pks.shoppingapp.core.presentation.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repo: AuthenticationRepo) {

    fun updateUserData(userData: UserData): Flow<ResultState<UpdateUserDataState>> {
        return repo.updateUserData(userData)
    }
}