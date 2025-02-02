package com.pks.shoppingapp.personalization.address.domain.repo

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.address.domain.model.AddressModel
import com.pks.shoppingapp.personalization.address.presentation.AddressState
import kotlinx.coroutines.flow.Flow

interface AddressRepo {
   suspend fun getAddresses():Flow<ResultState<AddressState>>
   suspend fun addAddresses(address:AddressModel):Flow<ResultState<AddressState>>
}