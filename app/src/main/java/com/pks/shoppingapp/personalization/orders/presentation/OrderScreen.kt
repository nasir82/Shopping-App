package com.pks.shoppingapp.personalization.orders.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pks.shoppingapp.personalization.orders.domain.model.OrderModel


@Composable
fun OrderScreen(nav: NavHostController,modifier: Modifier = Modifier) {
    val viewModel: OrderViewModel = hiltViewModel()
    val state = viewModel.orderState.collectAsState()   .value
    Scaffold(
        topBar = {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier =Modifier.padding(top = 40.dp, bottom = 5.dp, start = 10.dp)) {
                IconButton(onClick = {
                    nav.popBackStack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
                Text(text = "Orders")
            }
        }
    ) {
        inPad->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(inPad)) {

            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }else  if (state.orders.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "You have no oder yet. Make one")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.orders) { currentOrder ->
                        OrderCard(order = currentOrder)
                    }
                }
            }
        }

    }
}


@Composable
fun OrderCard(modifier: Modifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 8.dp, vertical = 4.dp),
              order: OrderModel
) {
   Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
       // show an image
       Icon(imageVector = Icons.Default.DeliveryDining, contentDescription = "", modifier = Modifier.size(48.dp))
       Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
          Text(text = order.status)
           Text(text = order.date)
       }

      if(order.status != "Delivered") TextButton(onClick = {

      }) {
           Text(text = "Cancel")
       }
   }
}