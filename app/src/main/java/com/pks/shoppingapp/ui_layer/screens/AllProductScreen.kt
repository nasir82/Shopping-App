package com.pks.shoppingapp.ui_layer.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.components.ShoppingTextField
import com.pks.shoppingapp.components.WishListAndSeeMoreCard
import com.pks.shoppingapp.ui_layer.navigation.NavDestinations


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AllProductScreen(modifier: Modifier = Modifier,nav:NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "See more")
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    modifier = Modifier.size(18.dp).clickable {
                        nav.popBackStack()
                    }
                )
                Text(text = "See your favourite one")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "items")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Price")
                Row(modifier = Modifier.width(90.dp)) {

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShoppingTextField(
                    label = "",
                    value = "Search",
                    modifier = Modifier.weight(1f),
                    leadingIcon = Icons.Default.Search,
                    onLeadingClick = {},
                    trailingIcon = Icons.Default.Clear,
                    onTrailingClick = {

                    }) {

                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(20) {
                    WishListAndSeeMoreCard(){
                        nav.navigate(NavDestinations.ProductDetailsScreen)
                    }
                }

            }

            Spacer(modifier = Modifier.height(80.dp))

        }
    }
}