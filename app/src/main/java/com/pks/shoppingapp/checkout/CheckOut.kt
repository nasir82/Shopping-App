package com.pks.shoppingapp.checkout

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.cart.domain.model.CartModel
import com.pks.shoppingapp.cart.presentation.CartViewModel
import com.pks.shoppingapp.components.SectionHeading
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.navigation.NavDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOut(nav: NavHostController, cartViewModel: CartViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 8.dp)

        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Spacer(modifier = Modifier.height(10.dp))

            val state = cartViewModel.productState.collectAsState().value.products
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
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
                Text(
                    text = "Checkout",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .heightIn(max = 720.dp),
                    verticalArrangement = Arrangement.spacedBy((8.dp))
                ) {
                    items(state) {
                        CheckoutCard(cartItem = it)

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)) {
                       Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                           Text(
                               text = "subtotal",
                               style = MaterialTheme.typography.bodyMedium,
                               color = MaterialTheme.colorScheme.onBackground
                           )

                           Text(text = "\$850")
                       }
                       Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                           Text(
                               text = "Delivery cost",
                               style = MaterialTheme.typography.bodyMedium,
                               color = MaterialTheme.colorScheme.onBackground
                           )

                           Text(text = "\$50")
                       }
                       Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                           Text(
                               text = "Vat",
                               style = MaterialTheme.typography.bodyMedium,
                               color = MaterialTheme.colorScheme.onBackground
                           )

                           Text(text = "\$50")
                       }
                       Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                           Text(
                               text = "Total",
                               style = MaterialTheme.typography.bodyMedium,
                               color = MaterialTheme.colorScheme.onBackground
                           )

                           Text(text = "\$950")
                       }



                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)) {
                        SectionHeading(title = "Address", text = "Change") {

                        }
                        Text(
                            text = "Md. Nasir Uddin ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "01731812645 ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "SustGate, Akhalia, Sylhet. ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)) {
                        SectionHeading(title = "Payment Method", text = "Change") {

                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Icon(imageVector = Icons.Default.Payment, contentDescription = "")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Paypal",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                    }
                }
               
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(text = "Pay Now") {
                    nav.navigate(NavDestinations.PaymentGateway)
                }
                Spacer(modifier = Modifier.height(50.dp))
            }

        }
    }
}


@Composable
fun CheckoutCard(cartItem: CartModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
        .clip(shape = RoundedCornerShape(10.dp))
        .height(100.dp)
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

    }
}