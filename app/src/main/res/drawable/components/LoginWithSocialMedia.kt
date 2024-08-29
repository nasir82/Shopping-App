package com.pks.shoppingappadmin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun LoginWithSocialMedia(modifier: Modifier = Modifier,media:Int,text:String) {

    Button(onClick = { }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp), colors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,

    ),
        border = BorderStroke(width = 1.dp, color = Color(0xFFF68B8B))) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = media), contentDescription = "", modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = text, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.Black)
        }
    }

    
}