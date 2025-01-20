package com.pks.shoppingapp.products.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pks.shoppingapp.home.domain.model.ProductModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductAttribute(product: ProductModel,attributes: Map<String,String>, onClick: (String, String) -> Unit = { s: String, s1: String -> }) {
    Column(modifier = Modifier.fillMaxWidth()) {

        product.productAttribute.forEach {
                prodAt->
            Column {
                Text(text = prodAt.name, style = MaterialTheme.typography.bodyMedium,color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier. height(5.dp))
                FlowRow (horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(5.dp)){
                    prodAt.values.forEach {

                        val isSelected = attributes[prodAt.name] == it
                        val borderWidth = if (isSelected) 2.dp else 1.dp
                        val borderColor = if (isSelected) Color.Green else MaterialTheme.colorScheme.primary
                        Box(modifier = Modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .wrapContentSize()
                            .clickable { onClick.invoke(prodAt.name, it) }
                            .border(
                                color = borderColor,
                                width = borderWidth,
                                shape = RoundedCornerShape(5.dp)
                            )) {
                            Text(
                                text = it,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier. height(5.dp))
            }


        }

    }
}
