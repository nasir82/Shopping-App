package com.pks.shoppingapp.personalization.address.domain.usecase

import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.address.domain.repo.AddressRepo
import com.pks.shoppingapp.personalization.address.presentation.AddressState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(private  val addressRepo: AddressRepo) {
    suspend fun getAddress(): Flow<ResultState<AddressState>> {
        return addressRepo.getAddresses()
    }
}