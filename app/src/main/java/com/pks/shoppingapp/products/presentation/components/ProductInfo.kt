package com.pks.shoppingapp.products.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.calculate_discount
import com.pks.shoppingapp.products.presentation.DetailsViewModel


@Composable
fun ProductInfo(product: ProductModel,viewModel: DetailsViewModel, onClick:()->Unit) {
    val discount = calculate_discount(product)
    val status = viewModel.stockStatus.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color.Transparent)
        ) {
            Text(
                text = if(product.salePrice>0) "${product.salePrice}" else product.price.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(10.dp))
            if(product.salePrice>0) Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "${product.price}", textDecoration = TextDecoration.LineThrough, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = if (discount != null) "${discount}% Off" else "",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            else{
                Text(text = "${product.price}", style = MaterialTheme.typography.titleMedium)
            }
            
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Stock status: ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium,
                color = if (product.stock > 0) Color.Green else Color.Red
            )

        }

    }
}