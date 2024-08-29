package com.pks.shoppingapp.ui_layer.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.components.CategoryCart


@Composable
fun ALlCategoryScreenUi(modifier: Modifier = Modifier,nav:NavHostController) {

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(60.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription ="", modifier = Modifier.size(18.dp).clickable { nav.popBackStack() } )
            Text(text = "All categories")
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {

            items(20){
                CategoryCart(index = it)
            }
        }
    }
}