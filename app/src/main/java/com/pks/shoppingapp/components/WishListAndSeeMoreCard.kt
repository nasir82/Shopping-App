package com.pks.shoppingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.home.domain.model.ProductModel


@Composable
fun WishListAndSeeMoreCard(product: ProductModel, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            .height(125.dp)
            .clip(shape = RoundedCornerShape(12.dp))
    ) {


        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
                .clickable { onClick.invoke() }) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true) // Enables crossfade animation
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .width(75.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Transparent),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = product.title,
                        maxLines = 2,
                        lineHeight = TextUnit(value = 14f, type = TextUnitType.Sp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = product.brand.name, style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "XL", style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Blue", style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                }
            }

            Row(modifier = Modifier.width(40.dp), horizontalArrangement = Arrangement.End) {
                Text(
                    text = product.salePrice.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(5.dp))

            Row(modifier = Modifier.width(60.dp), horizontalArrangement = Arrangement.End) {

            }

        }
    }
}