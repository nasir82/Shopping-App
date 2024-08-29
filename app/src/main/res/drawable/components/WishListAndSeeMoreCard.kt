package com.pks.shoppingappadmin.components

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pks.shoppingappadmin.R


@Preview(showBackground = true)
@Composable
fun WishListAndSeeMoreCard(modifier: Modifier = Modifier,onClick:()->Unit={}) {
    Box(modifier = Modifier.fillMaxWidth().height(125.dp)){


        Row(modifier = Modifier.fillMaxSize().padding(bottom = 10.dp).clickable { onClick.invoke() }) {
            Image(
                painterResource(id = R.drawable.passion),
                contentDescription = "",
                modifier = Modifier.width(75.dp).clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Full sleeve TShirt with 7 Color. just amazing guys you can buy this with comfortness",
                        maxLines = 2,
                        lineHeight = TextUnit(value = 14f, type = TextUnitType.Sp),
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp
                    )
                    Text(text = "CFEDL9")
                    Text(text = "XL")
                    Text(text = "Blue")

                }
            }

            Row(modifier = Modifier.width(40.dp), horizontalArrangement = Arrangement.End) {
                Text(text ="500")
            }
            Spacer(modifier = Modifier.width(5.dp))

            Row(modifier = Modifier.width(90.dp), horizontalArrangement = Arrangement.End) {

            }

        }
    }
}