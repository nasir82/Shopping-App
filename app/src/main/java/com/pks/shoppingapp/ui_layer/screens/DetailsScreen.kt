package com.pks.shoppingapp.ui_layer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.R
import com.pks.shoppingapp.components.SectionHeading
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.home.domain.model.ProductAttributeModel


@Composable
fun DetailsScreen(modifier: Modifier = Modifier, nav: NavHostController) {

    val attributes = listOf(
        ProductAttributeModel(
            name = "Color",
            values = listOf(
                "Black","White","Yellow"
            )
        ),
        ProductAttributeModel(
            name = "Size",
            values = listOf(
                "S","M","L","XL","XXL"
            )
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .border(width = 2.dp, color = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mensuit),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(height = 400.dp, width = 500.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    modifier = Modifier
                        .offset(x = 20.dp, y = 60.dp)
                        .clickable {
                            nav.popBackStack()
                        })

            }

            //Price title rating and favlist add

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                ProductInfo()
                Spacer(modifier = Modifier.height(16.dp))
                //product attributes
                attributes.forEach {attri->
                    ProductAttributes(attributes = attri)
                }
                // Product brand info
                Spacer(modifier = Modifier.height(10.dp))
                ProductBrand(imageUrl = "", name = "")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Specification ")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Dress\nMaterial: Linen\nMaterial composition: 100% Lilen\nPlease bear in mind that the photo may be slightly different from the actual item in terms of color due to lighting conditions or the display used to view")
                Spacer(modifier = Modifier.height(10.dp))
                SectionHeading(title = "Review & Rattings", text = "View")
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(text = "Buy", containerColor = Color(0xFFF68B8B)) {

                }
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(text = "Add to cart", containerColor = Color(0xFFD9D9D9)) {

                }
                Spacer(modifier = Modifier.height(10.dp))
//                ShoppingButton(text = "Add to wishlist") {
//
//                }

                Spacer(modifier = Modifier.height(80.dp))

            }
        }
    }
}


@Composable
fun ProductInfo(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box {
                Text(text = "20%")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Price")
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Product title")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Stock status")

    }
}


@Composable
fun ProductBrand(modifier: Modifier = Modifier,imageUrl:String,name:String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
        Image(imageVector = Icons.Default.Person, contentDescription = "", modifier = Modifier
            .size(24.dp)
            .clip(shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "Name of brand")
        Icon(imageVector = Icons.Default.Verified, contentDescription = "",Modifier.size(16.dp))
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductAttributes(modifier: Modifier = Modifier.fillMaxWidth(),attributes: ProductAttributeModel) {
  Column(modifier =modifier) {
      Text(text = attributes.name)
      Spacer(modifier = Modifier.height(4.dp))
      FlowRow(

      ) {
          attributes.values.forEach{
              Text(text = it, modifier = Modifier.padding(end = 5.dp))
          }
      }
  }
}

@Composable
fun NewComp(modifier: Modifier = Modifier) {

}