package com.pks.shoppingapp.products.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.home.presentation.ProductCart
import com.pks.shoppingapp.navigation.NavDestinations
import com.pks.shoppingapp.wishlist.utils.DataStoreViewModel

@Composable
fun CategoryBasedProduct(categoryName:String, nav: NavHostController, viewModel: HomeViewModel, detailsViewModel: DetailsViewModel) {
    val dataStoreViewModel:DataStoreViewModel = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        val products = viewModel.productState.collectAsState().value.products.filter {
            it.categoryId==categoryName
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = categoryName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products){
                    ProductCart(product = it,dataStoreViewModel) {
                        detailsViewModel.setProduct(it)
                        detailsViewModel.resetSelectedAttribute()
                        nav.navigate(NavDestinations.ProductDetailsScreen(productId = it.id))
                    }
                }
            }

        }
    }
}