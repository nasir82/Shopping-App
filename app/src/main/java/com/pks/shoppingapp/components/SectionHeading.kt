package com.pks.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SectionHeading(title:String,text:String,onClick:()-> Unit = {}) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
        TextButton(onClick = {
            onClick.invoke()
        }) {
            Text(text = text, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground)
        }

    }
}