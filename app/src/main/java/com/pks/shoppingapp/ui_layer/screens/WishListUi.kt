package com.pks.shoppingapp.ui_layer.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pks.shoppingapp.components.WishListAndSeeMoreCard
import com.pks.shoppingapp.ui_layer.navigation.NavDestinations

@Preview(showBackground = true)
@Composable
fun WishListScreenUI(modifier: Modifier = Modifier,nav:NavHostController= rememberNavController()) {

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)){

            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(text = "Wish list")
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "items")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Price")
                    Row(modifier = Modifier.width(90.dp)) {

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(20){
                        WishListAndSeeMoreCard(){
                            nav.navigate(NavDestinations.ProductDetailsScreen)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))
            }


        }
}