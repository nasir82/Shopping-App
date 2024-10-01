package com.pks.shoppingapp.products.presentation

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.cart.presentation.CartViewModel
import com.pks.shoppingapp.components.SectionHeading
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.home.domain.model.BrandModel
import com.pks.shoppingapp.home.domain.model.ProductAttributeModel
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.wishlist.presentation.WishListViewModel


@Composable
fun DetailsScreen(
    nav: NavHostController,
    productId: String,
    viewModel: HomeViewModel,
    wishListViewModel: WishListViewModel,
    cartViewModel: CartViewModel
) {
    val product = viewModel.productState.collectAsState().value.products.firstOrNull {
        it.id == productId
    }
    val attributes = listOf(
        ProductAttributeModel(
            name = "Color",
            values = listOf(
                "Black", "White", "Yellow"
            )
        ),
        ProductAttributeModel(
            name = "Size",
            values = listOf(
                "S", "M", "L", "XL", "XXL"
            )
        )
    )

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
                        .data(product!!.thumbnail)
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

            }

            //Price title rating and favlist add

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                ProductInfo(product!!){
                    wishListViewModel.addToWishList(product)
                }
                Spacer(modifier = Modifier.height(16.dp))
                //product attributes
                product.productAttribute.forEach { attri ->
                    ProductAttributes(attributes = attri)
                }
                // Product brand info
                Spacer(modifier = Modifier.height(10.dp))
                ProductBrand(brand = product.brand)
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
                    cartViewModel.addToCart(product)
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
fun ProductInfo(product: ProductModel,onClick:()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
        ) {
            Box(modifier = Modifier.background(color = Color.Transparent)) {
                Text(
                    text = product.price.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box {
                Text(
                    text = "20% off",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = product.price.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {onClick.invoke() }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Stock status: ${product.stock}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

    }
}


@Composable
fun ProductBrand(brand: BrandModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(brand.image)
                .crossfade(true) // Enables crossfade animation
                .build(), contentDescription = "", modifier = Modifier
                .size(24.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = brand.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Icon(
            imageVector = Icons.Default.Verified,
            contentDescription = "",
            Modifier.size(16.dp),
            tint = Color.Blue
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductAttributes(
    modifier: Modifier = Modifier.fillMaxWidth(),
    attributes: ProductAttributeModel
) {
    Column(modifier = modifier) {
        Text(
            text = attributes.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        FlowRow(

        ) {
            attributes.values.forEach {
                Text(
                    text = it,
                    modifier = Modifier.padding(end = 5.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun NewComp(modifier: Modifier = Modifier) {

}