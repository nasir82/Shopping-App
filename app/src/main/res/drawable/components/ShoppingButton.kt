package com.pks.shoppingappadmin.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingButton(
    modifier: Modifier = Modifier.fillMaxWidth().height(56.dp),
    text: String,
    isShowBorder: Boolean = true,
    border: BorderStroke = BorderStroke(width = 1.dp, color = Color.LightGray),
    containerColor: Color = Color.White,
    contentColor: Color = Color.Black,
    disableContainerColor: Color = Color.White,
    disableContentColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = disableContentColor,
            disabledContainerColor = disableContainerColor
        ),
        border = if (isShowBorder) border else null,
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.background(color = Color.Transparent),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun ShoppingIconButton(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(12.dp))
        .background(shape = RoundedCornerShape(15.dp), color = Color.Transparent)
        .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp)),
    icon: ImageVector,
    size: Double = 48.0,
    containerColor: Color = Color.White,
    contentColor: Color = Color.Black,
    disableContainerColor: Color = Color.White,
    disableContentColor: Color = Color.Black,
    onClick: () -> Unit
) {
    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = disableContentColor,
            disabledContainerColor = disableContainerColor
        )
    ) {

        androidx.compose.material3.Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier.size(size.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun previewIconButton() {

    ShoppingIconButton(icon = Icons.Default.Person) {

    }
}
