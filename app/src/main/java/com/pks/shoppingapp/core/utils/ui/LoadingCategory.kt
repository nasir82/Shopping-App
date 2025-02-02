package com.pks.shoppingapp.core.utils.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pks.shoppingapp.core.presentation.components.shimmerEffect

@Composable
fun LoadingCategory(modifier: Modifier = Modifier) {
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
                Box(modifier = Modifier
                    .size(70.dp)
                    .clip(shape = CircleShape)
                    .shimmerEffect())
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(32.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
    }
}