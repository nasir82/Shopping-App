package com.pks.shoppingapp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingapp.home.domain.model.ProductModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun MultiItemCarouselWithIndicator(items:List<ProductModel>) {
//    val items = listOf(
//        R.drawable.img,
//        R.drawable.ic_google,
//        R.drawable.img,
//        R.drawable.ic_google,
//        R.drawable.img,
//        R.drawable.ic_google,
//        R.drawable.img,
//        R.drawable.ic_google,
//        R.drawable.img
//    )
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val totalItems = items.size
    val firstVisibleItemIndex = listState.firstVisibleItemIndex
    val firstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset
    val itemWidthPx = with(LocalDensity.current) {
        100.dp.toPx() + 8.dp.toPx()  // card width + spacing
    }

    var fractionScrolled = remember {
       mutableStateOf( firstVisibleItemScrollOffset / itemWidthPx)
    }



    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        change.consume()
                        coroutineScope.launch {
                            listState.scrollBy(-dragAmount)
                        }
                    }
                },
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(items) { index,item ->
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .width(100.dp)
                        .height(120.dp)
                        .clickable {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index)
                                fractionScrolled.value = (index - firstVisibleItemIndex.dp.value) * 1.0f
                            }
                        }
                        .padding(end = 10.dp, top = 5.dp, bottom = 5.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.thumbnail)
                            .crossfade(true) // Enables crossfade animation
                            .build(),
                        contentDescription = "",
                        modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dot indicator calculation


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(totalItems) { index ->
                val selected = (index == firstVisibleItemIndex + fractionScrolled.value.roundToInt())
                Box(
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index)
                                fractionScrolled.value = (index - firstVisibleItemIndex.dp.value) * 1.0f
                            }
                        }
                        .size(if (selected) 16.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (selected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}
