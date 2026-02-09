package com.pks.shoppingapp.personalization.address.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.address.domain.model.AddressModel
import com.pks.shoppingapp.personalization.address.domain.usecase.AddAddressUseCase
import com.pks.shoppingapp.personalization.address.domain.usecase.GetAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(val addAddressUseCase: AddAddressUseCase,val getAddressUseCase: GetAddressUseCase): ViewModel() {

    val addresses = listOf(
        AddressModel("Alice Smith", "9876543210", "742 Evergreen Terrace", "12345", "Springfield", "Illinois", "USA"),
        AddressModel("Ravi Kumar", "919876543210", "MG Road", "560001", "Bangalore", "Karnataka", "India"),
        AddressModel("Emma Johnson", "447912345678", "10 Downing Street", "SW1A 2AA", "London", "England", "UK"),
        AddressModel("Liu Wei", "8613912345678", "Nanjing East Road", "200002", "Shanghai", "Shanghai", "China")
    )

    private val _addressState = MutableStateFlow(AddressState())
    val addressState = _addressState.asStateFlow()

    private val _addAddressState = MutableStateFlow(AddressAddingState())
    val addAddressState = _addAddressState.asStateFlow()
    init {
        getAddress()
    }

    fun getAddress(){
        viewModelScope.launch {
            _addressState.value = AddressState(isLoading = true)
            getAddressUseCase.getAddress().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _addressState.value = AddressState(errorMessage = it.message)
                    }

                    ResultState.Loading -> {
                        _addressState.value = AddressState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        _addressState.value = AddressState(userAddresses = it.data.userAddresses)
                        println(_addressState.value)

                    }
                }
            }
        }
    }

    fun addUserAddress(address:AddressModel){
        viewModelScope.launch {
           addAddressUseCase.addAddress(address).collectLatest {
               when (it) {
                   is ResultState.Error -> {
                        _addAddressState.value = AddressAddingState(errorMessage = it.message)
                   }

                   ResultState.Loading -> {
                      _addAddressState.value = AddressAddingState(isLoading = true)
                   }

                   is ResultState.Success -> {
                       var list = _addressState.value.userAddresses.toMutableList()
                       list.add(address)
                       _addressState.value = AddressState(userAddresses = list)
                       _addAddressState.value = AddressAddingState(isSuccess = true)

                   }
               }
           }

        }
    }


    fun resetAddAddressState(){
        _addAddressState.value = AddressAddingState()
    }

}