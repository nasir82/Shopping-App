package com.pks.shoppingapp.ui_layer.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pks.shoppingapp.components.CartCard
import com.pks.shoppingapp.components.ShoppingButton

@Preview(showBackground = true)
@Composable
fun CartScreenUI(modifier: Modifier = Modifier) {

    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80
    Box(
        modifier = Modifier

            .offset(x = (x).dp, y = (-200).dp)
            .background(color = Color(0xFFF68B8B), shape = CircleShape)
            .size(size = 350.dp)
            .clip(
                shape = CircleShape
            )
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Shopping Cart", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "", modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(1.dp))
            Text(text = "Continue shopping")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Item")
            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.width(40.dp), horizontalArrangement = Arrangement.End) {
                Text(text ="Price")
            }
            Spacer(modifier = Modifier.width(5.dp))

            Row(modifier = Modifier.width(40.dp), horizontalArrangement = Arrangement.End) {
                Text(text ="QTY")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Row(modifier = Modifier.width(50.dp), horizontalArrangement = Arrangement.End) {
                Text(text ="Price")
            }


        }

        LazyColumn(modifier = Modifier
            .weight(1f)) {
                items(20){
                    CartCard()
                }

        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(text = "Total Amount")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "500000")
        }
        Spacer(modifier = Modifier.height(10.dp))
        ShoppingButton(text = "checkout", containerColor = Color(0xFFF68B8B)) {

        }
        Spacer(modifier = Modifier.height(80.dp))
    }

    
}