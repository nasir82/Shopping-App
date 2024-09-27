package com.pks.shoppingapp.authentication.presentation.profile

import com.pks.shoppingapp.authentication.domain.model.UserData

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val errorMessage:String? = null,
    val userData: UserData? = null
)