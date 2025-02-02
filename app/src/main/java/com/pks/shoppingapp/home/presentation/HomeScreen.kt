package com.pks.shoppingapp.home.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.core.presentation.components.MultiItemCarouselWithIndicator
import com.pks.shoppingapp.core.presentation.components.SearchBar
import com.pks.shoppingapp.core.presentation.components.SectionHeading
import com.pks.shoppingapp.core.presentation.components.shimmerEffect
import com.pks.shoppingapp.core.utils.ui.LoadingCategory
import com.pks.shoppingapp.home.domain.model.ProductModel
import com.pks.shoppingapp.navigation.NavDestinations
import com.pks.shoppingapp.products.presentation.DetailsViewModel
import com.pks.shoppingapp.wishlist.utils.DataStoreViewModel


@Composable
fun HomeScreenUi(
    nav: NavHostController,
    homeViewmodel: HomeViewModel,
    detailsViewModel: DetailsViewModel,
    dataStoreViewModel: DataStoreViewModel
) {


    val productState = homeViewmodel.productState.collectAsState().value
    val categoryState = homeViewmodel.categoryState.collectAsState().value

    // now fetch the favourite products using product viewmodel
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)

        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar()
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "")

            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                SectionHeading(title = "Category", text = "See more") {
                    Log.d("tag", "clicked in see more")
                    nav.navigate(NavDestinations.AllCategory)
                }
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    if (categoryState.isLoading) {
                        items(6) {
                            LoadingCategory()
                        }
                    } else {
                        items(categoryState.categories.take(5)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(end = 8.dp)
                                    .clickable {
                                        nav.navigate(
                                            NavDestinations.CategoryBasedProducts(
                                                categoryName = it.id
                                            )
                                        )
                                    }
                            ) {
                                Card(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(shape = CircleShape)
                                        .border(
                                            width = 1.dp,
                                            color = Color.Gray,
                                            shape = CircleShape
                                        ),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.Transparent
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(it.image)
                                                .crossfade(true) // Enables crossfade animation
                                                .build(),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(70.dp)
                                                .clip(shape = CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = it.name,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    modifier = Modifier.width(70.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if(productState.isLoading) LazyRow {
                    items(5){
                        Box (modifier = Modifier.height(150.dp), contentAlignment = Alignment.CenterStart){
                            Box(
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .height(120.dp)
                                    .width(100.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .shimmerEffect()
                            )
                        }
                    }
                }
                else MultiItemCarouselWithIndicator(items = productState.products.take(6),nav=nav,detailsViewModel=detailsViewModel)

                Spacer(modifier = Modifier.height(16.dp))

                SectionHeading(title = "Flash Sale", text = "See more") {
                    nav.navigate(NavDestinations.AllProductScreen)
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    if(productState.isLoading)
                        items(5){
                            LoadingProducts()
                        }
                    else items(productState.products.take(2)) { product ->
                        ProductCart(product = product,dataStoreViewModel = dataStoreViewModel){
                            detailsViewModel.setProduct(product)
                            nav.navigate(NavDestinations.ProductDetailsScreen(productId = product.id))
                        }
                    }
                }


            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


@Composable
fun LoadingProducts() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier

            .padding(end = 10.dp)
            .width(100.dp)
            .height(140.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(modifier = Modifier

            .padding(end = 10.dp)
            .width(100.dp)
            .height(140.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .shimmerEffect()
        )
    }
}



@Composable
fun ProductCart(product:ProductModel,dataStoreViewModel: DataStoreViewModel? = null, onClick:()->Unit={}) {
    val discount = calculate_discount(product)
    val config = LocalConfiguration.current
    val context = LocalContext.current
    val favourites = dataStoreViewModel?.favorites?.collectAsState()?.value ?: emptyMap()
    Box(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 10.dp)
        .clip(shape = RoundedCornerShape(8.dp))
        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
        .size(height = 270.dp, width = 180.dp)
        .clickable {
            onClick.invoke()
        }) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)) {
            Spacer(modifier = Modifier.height(5.dp))
            Card(shape = RoundedCornerShape(8.dp), modifier = Modifier .align(alignment = Alignment.CenterHorizontally)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.thumbnail)
                        .crossfade(true) // Enables crossfade animation
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(height = 150.dp, width = 160.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.title, style = TextStyle(fontWeight = FontWeight.W600, fontSize = 13.sp), maxLines = 2, overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = product.brand.name,  color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Default.NightsStay, contentDescription = "", modifier = Modifier.size(14.dp))
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                if(product.salePrice>0) Text(text = "${product.salePrice}", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium) else Text(text = "${product.price}", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.weight(1f))
               Box (modifier = Modifier
                   .padding(end = 5.dp)
                   .size(20.dp)
                   .clip(shape = RoundedCornerShape(topStart = 5.dp, bottomEnd = 5.dp))
                   .background(color = MaterialTheme.colorScheme.onBackground), contentAlignment = Alignment.Center){
                    Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = MaterialTheme.colorScheme.primary)
               }
            }
        }
        if( discount != null) Box (modifier = Modifier
            .padding(10.dp)
            .background(color = MaterialTheme.colorScheme.primary)
            .offset(x = 0.dp, y = 0.dp)
            .wrapContentSize(), contentAlignment = Alignment.Center){
            Text(
                text = "$discount% off",
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Icon(imageVector = if(favourites[product.id]== null) Icons.Default.FavoriteBorder else Icons.Default.Favorite, contentDescription = "", modifier = Modifier
            .offset(x = 150.dp, y = 10.dp)
            .clickable {
                dataStoreViewModel?.toggleFavorite(product.id)
                val txt = if(favourites[product.id]== null) "Added to the wishlist" else "Remove from wishlist"
                Toast
                    .makeText(context, txt, Toast.LENGTH_SHORT)
                    .show()
            })
    }
}


fun calculate_discount(product:ProductModel):String?{
   val discount:String? = if(product.salePrice>0){
       (((product.price - product.salePrice)/product.price)*100).toInt().toString()
    }else null

    return  discount
}



fun uploadProduct(){
//    val firestore = FirebaseFirestore.getInstance()
//    val ref = firestore.collection("Products").document().set(hardcodedProducts[0])
//    val ref2 = firestore.collection("Products").document().set(hardcodedProducts[1])
}

