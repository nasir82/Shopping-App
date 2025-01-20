package com.pks.shoppingapp.products.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.cart.presentation.CartViewModel
import com.pks.shoppingapp.components.SectionHeading
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.home.presentation.calculate_discount
import com.pks.shoppingapp.products.presentation.components.ProductAttribute
import com.pks.shoppingapp.products.presentation.components.ProductBrand
import com.pks.shoppingapp.products.presentation.components.ProductInfo
import com.pks.shoppingapp.wishlist.presentation.WishListViewModel


@Composable
fun DetailsScreen(
    nav: NavHostController,
    productId: String,
    viewModel: HomeViewModel,
    wishListViewModel: WishListViewModel,
    cartViewModel: CartViewModel,
    detailsViewModel: DetailsViewModel
) {
    val product = viewModel.productState.collectAsState().value.products.firstOrNull {
        it.id == productId
    }
    val attributes = detailsViewModel.selectedAttributes.collectAsState().value
    val allImages =  detailsViewModel.getAllImages()
    val discount = calculate_discount(product!!)
   // val selectedAttributes = detailsViewModel.selectedAttributes.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .border(width = 2.dp, color = Color.Black)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(detailsViewModel.selectedImage.collectAsState().value)
                        .crossfade(true) // Enables crossfade animation
                        .build(),
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
                        },
                    tint = MaterialTheme.colorScheme.onBackground
                )

                if(allImages.isNotEmpty())
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 0.dp, y = 310.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(allImages){image->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(image)
                                    .crossfade(true) // Enables crossfade animation
                                    .build(),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(5.dp))
                                    .size(height = 80.dp, width = 50.dp)
                                    .clickable {
                                        detailsViewModel.setSelectedImage(image)
                                    }
                            )
                        }
                    }

            }

            //Price title rating and favlist add

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                ProductInfo(product,detailsViewModel){
                    wishListViewModel.addToWishList(product)
                }
                Spacer(modifier = Modifier.height(16.dp))
                //product attributes
                // Product brand info
                Spacer(modifier = Modifier.height(10.dp))
                ProductBrand(brand = product.brand)
                Spacer(modifier = Modifier.height(10.dp))
                ProductAttribute(product,attributes){
                    name, attribute->
                    detailsViewModel.onSelectedAttribute(product,name,attribute)

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Description ",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(10.dp))
                SectionHeading(title = "Review & Rattings", text = "View")
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(
                    text = "Buy",
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {

                }
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(
                    text = "Add to cart",
                    containerColor = Color(0xFFD9D9D9),
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    val cartItem = cartViewModel.convertIntoCartItem(product,1,detailsViewModel.selectedVariation.value)
                    cartViewModel.addToCart(cartItem)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.height(80.dp))

            }
        }
    }
}


