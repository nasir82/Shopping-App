package com.pks.shoppingapp.checkout

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor():ViewModel() {

    val paymentMethods = listOf(
        PaymentModel(icon = "Icon1","PayPal"),
        PaymentModel(icon = "Icon1","Bkash"),
        PaymentModel(icon = "Icon1","Nagad"),
        PaymentModel(icon = "Icon1","Upay"),
        PaymentModel(icon = "Icon1","EasyPay"),
        PaymentModel(icon = "Icon1","Airtm")
    )
}