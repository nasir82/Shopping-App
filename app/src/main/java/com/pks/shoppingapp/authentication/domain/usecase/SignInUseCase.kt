package com.pks.shoppingapp.authentication.domain.usecase

import com.pks.shoppingapp.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingapp.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repo: AuthenticationRepo) {
    fun singIn(email:String,password:String): Flow<ResultState<String>> {
        return repo.signInWithEmailAndPassword(email=email,password=password)
    }
}