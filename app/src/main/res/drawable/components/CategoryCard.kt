package com.pks.shoppingappadmin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pks.shoppingappadmin.R


@Preview(showBackground = true)
@Composable
fun CategoryCart(modifier: Modifier = Modifier,index:Int=0) {

    val paddingValues = if(index%2==0){
        PaddingValues(end = 5.dp)
    }else{
        PaddingValues(start = 5.dp)
    }
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(paddingValues)
        .height(280.dp), shape = RoundedCornerShape(15.dp), colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )){
        Column(modifier = Modifier.fillMaxSize()) {
            Image(painterResource(id = R.drawable.passion ), contentDescription = "", modifier = Modifier.weight(1f).clip(shape = RoundedCornerShape(10.dp)), contentScale = ContentScale.Crop)
            Text(text = "Name of product category")
        }

    }
}
@Preview(showBackground = true)
@Composable
fun AllProductCart(modifier: Modifier = Modifier) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .height(280.dp), shape = RoundedCornerShape(15.dp), colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(5.dp)){
        Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            Image(painterResource(id = R.drawable.passion ), contentDescription = "", modifier = Modifier.weight(1f), contentScale = ContentScale.Crop)
            Text(text = "Name of product category")
            Text(text = "Name of product category")
        }

    }
}