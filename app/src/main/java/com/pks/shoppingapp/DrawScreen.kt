package com.pks.shoppingapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Drawing(modifier: Modifier = Modifier) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .drawBehind {

                val path = Path().apply {
                     moveTo(0f,300f)
                    quadraticTo(
                        x1 = -15f, // Control point X (can be adjusted to control the curve)
                        y1 = 285f, // Control point Y (can be adjusted to control the curve)
                        x2 = 30f, // End point X
                        y2 = 270f  // End point Y
                    )
                    lineTo(150f,210f)
                    lineTo(250f,250f)
                }
                drawPath(path = path, color = Color.Black)
            }
        ){


        }



}