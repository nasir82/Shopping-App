package com.pks.shoppingapp.paymentgateway

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.navigation.NavDestinations

@Composable
fun PaymentScreen(nav:NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 5.dp)){
            Spacer(modifier = Modifier.height(50.dp))

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(text = "Payment Successful")
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(text = "Continue") {
                    nav.navigate(NavDestinations.ShowOff){

                        popUpTo<NavDestinations.CheckOut>(){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}