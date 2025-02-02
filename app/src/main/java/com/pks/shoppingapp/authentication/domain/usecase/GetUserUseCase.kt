package com.pks.shoppingapp.authentication.domain.usecase

import com.pks.shoppingapp.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingapp.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingapp.core.presentation.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: AuthenticationRepo) {

    fun getUserWithUid(uid:String): Flow<ResultState<ProfileScreenState>> {
        return repo.getUserDataByUid(uid)
    }
}