package com.pks.shoppingapp.products.presentation

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.components.SearchBar
import com.pks.shoppingapp.components.WishListAndSeeMoreCard
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.navigation.NavDestinations


@Composable
fun AllProductScreen(nav:NavHostController,viewModel: HomeViewModel) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Products", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            nav.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "See your favourite one", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "items", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Price", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                Row(modifier = Modifier.width(60.dp)) {

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.productState.value.products) {
                    WishListAndSeeMoreCard(product = it){
                        nav.navigate(NavDestinations.ProductDetailsScreen(productId = it.id))
                    }
                }

            }

        }
    }
}