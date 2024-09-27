package com.pks.shoppingapp.home.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.R
import com.pks.shoppingapp.components.MultiItemCarouselWithIndicator
import com.pks.shoppingapp.components.SearchBar
import com.pks.shoppingapp.components.SectionHeading
import com.pks.shoppingapp.ui_layer.navigation.NavDestinations


@Composable
fun HomeScreenUi(
    nav: NavHostController,
    homeViewmodel: HomeViewModel
) {


    val productState = homeViewmodel.productState.collectAsState().value
    val categoryState = homeViewmodel.categoryState.collectAsState().value
    val value = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

        ) {
            Spacer(modifier = Modifier.height(45.dp))
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
                    .verticalScroll(rememberScrollState())
            ) {

                SectionHeading(title = "Category", text = "See more") {
                    Log.d("tag", "clicked in see more")
                    nav.navigate(NavDestinations.AllCategory)
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(categoryState.categories) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                                .wrapContentSize()
                                .padding(end = 8.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(shape = CircleShape)
                                    .border(width = 1.dp, color = Color.Gray, shape = CircleShape),
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
                Spacer(modifier = Modifier.height(10.dp))
                MultiItemCarouselWithIndicator(items = productState.products)

                Spacer(modifier = Modifier.height(16.dp))

                SectionHeading(title = "Flash Sale", text = "See more") {
                    nav.navigate(NavDestinations.AllProductScreen)
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(productState.products.take(5)) {product->
                        Column {
                            Card(
                                modifier = Modifier

                                    .padding(end = 10.dp)
                                    .width(100.dp)
                                    .height(140.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(product.thumbnail)
                                        .crossfade(true) // Enables crossfade animation
                                        .build(),
                                    contentDescription = "",
                                    modifier = Modifier

                                        .size(height = 140.dp, width = 100.dp),
                                    contentScale = ContentScale.FillHeight
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Transparent
                                ),
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .width(100.dp)
                                    .height(140.dp)

                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp, vertical = 10.dp)

                                        .verticalScroll(
                                            rememberScrollState()
                                        )
                                ) {

                                    Text(text = product.title, lineHeight = 15.sp)
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(text = product.brand.name, fontWeight = FontWeight.W600)
                                    Text(text = product.salePrice.toString())
                                    Text(
                                        text = AnnotatedString.Builder().apply {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Red
                                                )
                                            ) {
                                                append("BDT 5000 ")
                                            }
                                            append("off ")
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Green
                                                )
                                            ) {
                                                append("20%")
                                            }
                                        }.toAnnotatedString(),
                                        style = TextStyle(fontSize = 12.sp)
                                    )

                                }
                            }

                        }
                    }
                }


            }
        }
    }
}


@Composable
fun CategoryItemCard(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(60.dp)
                .clip(shape = CircleShape)
                .border(width = 1.dp, color = Color.Gray, shape = CircleShape),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_google),
                    contentDescription = "",
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Suit",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(end = 15.dp)
        )
    }
}

