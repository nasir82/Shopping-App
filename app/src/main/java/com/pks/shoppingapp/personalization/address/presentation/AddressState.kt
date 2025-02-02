package com.pks.shoppingapp.personalization.address.presentation

import com.pks.shoppingapp.personalization.address.domain.model.AddressModel

data class AddressState(
    val isLoading: Boolean = false,
    val errorMessage:String? = null,
    val userAddresses: List<AddressModel> = emptyList()
)