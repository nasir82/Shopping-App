package com.pks.shoppingapp.cart.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.pks.shoppingapp.components.CartCard
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.components.shimmerEffect


@Composable
fun CartScreenUI(modifier: Modifier = Modifier, cartViewModel: CartViewModel) {

    val state = cartViewModel.productState.collectAsState().value
    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80
    Box(
        modifier = Modifier

            .offset(x = (x).dp, y = (-200).dp)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            .size(size = 350.dp)
            .clip(
                shape = CircleShape
            )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Shopping Cart",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                text = "Continue shopping",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Item",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.width(50.dp), horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Price",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(5.dp))

            Row(modifier = Modifier.width(40.dp), horizontalArrangement = Arrangement.End) {
                Text(
                    text = "QTY",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Row(modifier = Modifier.width(70.dp), horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Cost",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }


        }
        Spacer(modifier = Modifier.height(8.dp))
        if (state.isLoading) {
            Log.d("isLoadinData","Yes")
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(5) {
                    LoadingBox()
                }

            }
        } else if (state.error.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(state.products) {
                    CartCard(product = it)
                }

            }
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                text = "Total Amount",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = "500000",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        ShoppingButton(
            text = "checkout",
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onBackground
        ) {

        }
        Spacer(modifier = Modifier.height(80.dp))
    }


}

@Composable
fun LoadingBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(bottom = 10.dp)
            .height(125.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .shimmerEffect()
    )

}