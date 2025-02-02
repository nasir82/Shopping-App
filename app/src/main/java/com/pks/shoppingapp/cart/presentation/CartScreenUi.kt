package com.pks.shoppingapp.cart.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.cart.domain.model.CartModel
import com.pks.shoppingapp.core.presentation.components.ShoppingButton
import com.pks.shoppingapp.core.presentation.components.shimmerEffect
import com.pks.shoppingapp.navigation.NavDestinations


@Composable
fun CartScreenUI(nav:NavHostController, cartViewModel: CartViewModel) {

    val state = cartViewModel.productState.collectAsState().value
    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80
    val totalCost =  String.format( "%.2f", cartViewModel.totalCost.collectAsState().value)
    Box(
        modifier = Modifier

            .offset(x = (x).dp, y = (-200).dp)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            .size(size = 350.dp)
            .clip(
                shape = CircleShape
            )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Shopping Cart",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                text = "Continue shopping",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (state.isLoading) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),

            ) {
                items(16) {
                    LoadingBox()
                }

            }
        } else if (state.error.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(.8f)
                ) {
                items(state.products) {
                    CartCardFinal(cartItem = it, cartViewModel = cartViewModel)
                }

            }

        }

        if(state.products.isNotEmpty()){
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = "Total Amount",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "$totalCost",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            ShoppingButton(
                text = "CheckOut",
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onBackground
            ) {
                nav.navigate(NavDestinations.CheckOut)
            }
        }

        Spacer(modifier = Modifier.height(90.dp))
    }


}

@Composable
fun LoadingBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(bottom = 10.dp)
            .height(125.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .shimmerEffect()
    )

}



//
//@Composable
//fun CartProduct(cartModel: CartModel, onClick:()->Unit={}) {
//    val discount = calculate_discount(cartModel.product)
//    val config = LocalConfiguration.current
//    val context = LocalContext.current
//    Box(modifier = Modifier
//        .padding(horizontal = 8.dp, vertical = 10.dp)
//        .clip(shape = RoundedCornerShape(8.dp))
//        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
//        .size(height = 270.dp, width = 180.dp)
//        .clickable {
//            onClick.invoke()
//        }) {
//
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 5.dp)) {
//            Spacer(modifier = Modifier.height(5.dp))
//            Card(shape = RoundedCornerShape(8.dp), modifier = Modifier .align(alignment = Alignment.CenterHorizontally)) {
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(cartModel.image)
//                        .crossfade(true) // Enables crossfade animation
//                        .build(),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .size(height = 150.dp, width = 160.dp),
//                    contentScale = ContentScale.Crop
//                )
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text =cartModel.title, style = TextStyle(fontWeight = FontWeight.W600, fontSize = 13.sp), maxLines = 2, overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.onBackground)
//            Spacer(modifier = Modifier.height(5.dp))
//            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//                Text(text = cartModel.brandName,  color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium)
//                Spacer(modifier = Modifier.width(10.dp))
//                Icon(imageVector = Icons.Default.NightsStay, contentDescription = "", modifier = Modifier.size(14.dp))
//            }
//            Spacer(modifier = Modifier.height(5.dp))
//            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//               Text(text = "${cartModel. price}", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium)
//                Spacer(modifier = Modifier.weight(1f))
//                Box (modifier = Modifier
//                    .padding(end = 5.dp)
//                    .size(20.dp)
//                    .clip(shape = RoundedCornerShape(topStart = 5.dp, bottomEnd = 5.dp))
//                    .background(color = MaterialTheme.colorScheme.onBackground), contentAlignment = Alignment.Center){
//                    Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = MaterialTheme.colorScheme.primary)
//                }
//            }
//        }
//        if( discount != null) Box (modifier = Modifier
//            .padding(10.dp)
//            .background(color = MaterialTheme.colorScheme.primary)
//            .offset(x = 0.dp, y = 0.dp)
//            .wrapContentSize(), contentAlignment = Alignment.Center){
//            Text(
//                text = "$discount% off",
//                modifier = Modifier.padding(5.dp),
//                style = MaterialTheme.typography.bodyLarge,
//                color = MaterialTheme.colorScheme.onBackground
//            )
//        }
//    }
//}

@Composable
fun CartCardFinal(cartItem:CartModel,cartViewModel: CartViewModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
        .clip(shape = RoundedCornerShape(10.dp))
        .height(110.dp)
        .padding(horizontal = 5.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        
       Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
           AsyncImage(
               model = ImageRequest.Builder(LocalContext.current)
                   .data(cartItem.image)
                   .crossfade(true) // Enables crossfade animation
                   .build(),
               contentDescription = "",
               modifier = Modifier
                   .clip(shape = RoundedCornerShape(5.dp))
                   .size(height = 60.dp, width = 60.dp),
               contentScale = ContentScale.Crop
           )
           HorizontalDivider(modifier = Modifier.width(10.dp))
           Column {
               Text(text = cartItem.title,style = MaterialTheme.typography.titleMedium, color =  MaterialTheme.colorScheme.onBackground, maxLines = 1, overflow = TextOverflow.Ellipsis)
               Text(text =if(cartItem.selectedVariationAttributes.isNotEmpty()) cartItem.selectedVariationAttributes.toString() else  cartItem.brandName,style = MaterialTheme.typography.bodySmall, color =  MaterialTheme.colorScheme.onBackground, maxLines = 1, overflow = TextOverflow.Ellipsis)
               
           }
          
       }
       
       Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
           IconButton(onClick = {
               cartViewModel.removeOneToCart(cartItem.id)
           }) {
               Icon(imageVector = Icons.Default.Remove, contentDescription = "", modifier = Modifier
                   .size(24.dp)
                   .clip(shape = CircleShape)
                   .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape))
           }
           Text(text = cartItem.quantity.toString(),style = MaterialTheme.typography.titleMedium, color =  MaterialTheme.colorScheme.onBackground, maxLines = 1, overflow = TextOverflow.Ellipsis)
           IconButton(onClick = {
               cartViewModel.addOneToCart(cartItem.id)
           }) {
               Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier
                   .size(24.dp)
                   .clip(shape = CircleShape)
                   .background(color = Color.Blue.copy(alpha = 0.5f), shape = CircleShape))
           }

           Spacer(modifier = Modifier.weight(1f))
           Text(text =  String.format("%.2f", cartItem.quantity * cartItem.price),style = MaterialTheme.typography.titleMedium, color =  MaterialTheme.colorScheme.onBackground, maxLines = 1, overflow = TextOverflow.Ellipsis)
       } 
        
        
    }
}
