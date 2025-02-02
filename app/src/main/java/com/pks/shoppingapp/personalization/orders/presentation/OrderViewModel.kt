package com.pks.shoppingapp.personalization.orders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingapp.core.presentation.ResultState
import com.pks.shoppingapp.personalization.orders.domain.model.OrderModel
import com.pks.shoppingapp.personalization.orders.domain.usecase.AddOrderUseCase
import com.pks.shoppingapp.personalization.orders.domain.usecase.GetOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    val addOrderUseCase: AddOrderUseCase,
    val getOrderUseCase: GetOrderUseCase
) : ViewModel() {
    private val _orderState = MutableStateFlow(OrderState())
    val orderState = _orderState.asStateFlow()

    init {
        getOrder()
    }

    fun getOrder() {
        viewModelScope.launch {
            getOrderUseCase.getOrders().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _orderState.value = OrderState(errorMessage = it.message)
                    }

                    ResultState.Loading -> {
                        _orderState.value = OrderState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        _orderState.value = OrderState(orders = it.data.orders)
                        println(_orderState.value)

                    }
                }
            }
        }
    }


    fun addOrder(order: OrderModel) {
        viewModelScope.launch {
            addOrderUseCase.addOrder(order).collectLatest {
                when (it) {
                    is ResultState.Error -> {

                    }

                    ResultState.Loading -> {
                        // _addressState.value = AddressState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        var list = _orderState.value.orders.toMutableList()
                        list.add(order)
                        _orderState.value = OrderState(orders = list)
                        println(_orderState.value)

                    }
                }
            }

        }
    }


}