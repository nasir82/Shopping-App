package com.pks.shoppingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.home.domain.model.ProductModel


@Composable
fun CartCard(modifier: Modifier = Modifier.fillMaxWidth(),product:ProductModel) {
  Box(modifier = modifier
      .padding(bottom = 10.dp)
      .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
      .height(125.dp))  {


      Column(modifier = Modifier.fillMaxWidth()) {

          Row(modifier = Modifier.fillMaxSize()) {
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
                      modifier = Modifier.fillMaxSize(),
                      verticalArrangement = Arrangement.Center
                  ) {
                      Text(
                          text = product.title,
                          maxLines = 2,
                          lineHeight = TextUnit(value = 14f, type = TextUnitType.Sp),
                          overflow = TextOverflow.Ellipsis,
                          fontSize = 12.sp
                      )
                      Text(text = product.brand.name)
                      Text(text = "XL")
                      Text(text = "Blue")

                  }
              }

              Row(modifier = Modifier.width(50.dp), horizontalArrangement = Arrangement.End) {
                  Text(text =product.salePrice.toString(), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground)
              }
              Spacer(modifier = Modifier.width(5.dp))

              Row(modifier = Modifier.width(30.dp), horizontalArrangement = Arrangement.Center) {
                  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
                      Text(text = "1")
                      Column {
                          Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.size(15.dp))
                          Spacer(modifier = Modifier.height(8.dp))
                          Icon(imageVector = Icons.Default.Remove, contentDescription = "", modifier = Modifier.size(15.dp))
                      }
                  }
              }
              Spacer(modifier = Modifier.width(5.dp))
              Row(modifier = Modifier.width(70.dp), horizontalArrangement = Arrangement.End) {
                  Text(text =product.salePrice.toString(), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground)
              }
          }
      }
  }
}