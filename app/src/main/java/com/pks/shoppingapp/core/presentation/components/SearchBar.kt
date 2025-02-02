package com.pks.shoppingapp.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    val searchState = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(12.dp)
            )
            .height(48.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(value = searchState.value,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground
            ),
            onValueChange = { searchState.value = it },
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(start = 16.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                    if (searchState.value.isEmpty()) {
                        Text(
                            text = "Search",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            modifier = Modifier
                                .wrapContentHeight()
                        )
                    } else {
                        innerTextField()
                    }
                }
            }
        )
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            modifier = Modifier
                .padding(end = 6.dp)
                .size(32.dp)
                .background(color = Color(0xFF509790), shape = RoundedCornerShape(16.dp))
                .padding(8.dp),
            tint = Color.White
        )
    }
}