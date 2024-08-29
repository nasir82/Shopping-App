package com.pks.shoppingapp.ui_layer.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.R
import com.pks.shoppingapp.components.ShoppingButton


@Composable
fun DetailsScreen(modifier: Modifier = Modifier,nav:NavHostController) {

    Box(modifier = Modifier
        .fillMaxSize()) {
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 20.dp, y = 320.dp)
                ) {
                    Text(text = "Men Premium suit", fontSize = 18.sp, fontWeight = FontWeight.W500, color = Color.Blue)
                    Text(text = "4.8 *", fontSize = 18.sp, fontWeight = FontWeight.W700, color = Color.Blue)
                    Text(text = "2500 BDT", fontSize = 18.sp, fontWeight = FontWeight.W600, color = Color.Blue)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Size")
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "M")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "L")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "XL")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "XXL")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Remove, contentDescription = "")
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "3")
                        }
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Color")
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = Color.Red)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = Color.White)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = Color.Yellow)
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Specification ")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Dress\nMaterial: Linen\nMaterial composition: 100% Lilen\nPlease bear in mind that the photo may be slightly different from the actual item in terms of color due to lighting conditions or the display used to view")
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(text = "Buy", containerColor = Color(0xFFF68B8B)) {

                }
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(text = "Add to cart", containerColor = Color(0xFFD9D9D9)) {

                }
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingButton(text = "Add to wishlist") {

                }
                
                Spacer(modifier = Modifier.height(80.dp))

            }
        }
    }
}