package com.pks.shoppingapp.wishlist.presentation

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.components.WishListAndSeeMoreCard
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.navigation.NavDestinations


@Composable
fun WishListScreenUI(viewModel: HomeViewModel, nav:NavHostController,wishListViewModel: WishListViewModel) {

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)){

            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Wish list", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "items", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Price", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground)
                    Row(modifier = Modifier.width(60.dp)) {

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(wishListViewModel.productState.value.products){
                        WishListAndSeeMoreCard(it){
                            nav.navigate(NavDestinations.ProductDetailsScreen(it.id))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))
            }


        }
}