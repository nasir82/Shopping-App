package com.pks.shoppingapp.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pks.shoppingapp.R
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.navigation.NavDestinations



@Composable
fun SuccessScreen(navDestinations: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF797988)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(360.dp)
                .width(350.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(15.dp))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape)
                        .background(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(56.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Success",  style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,)
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Congratulations, you have completed your registration!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                ShoppingButton(text = "Done", containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onBackground) {
                    navDestinations.navigate(NavDestinations.ShowOff){
                        popUpTo(NavDestinations.SignUpScreen){
                            inclusive = true
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ShoppingAlertDialog(modifier: Modifier = Modifier, onCancel: () -> Unit, onConfirm: () -> Unit) {

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            ShoppingButton(text = "Log out", modifier = Modifier.wrapContentWidth()) {
                onConfirm.invoke()
            }
        },
        dismissButton = {
            ShoppingButton(text = "Cancel", modifier = Modifier.wrapContentWidth()) {
                onCancel.invoke()
            }
        },
        shape = RoundedCornerShape(15.dp),
        title = {


            Column(
                modifier = Modifier
                    .height(250.dp)
                    .width(350.dp)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painterResource(id = R.drawable.profile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Log Out", color = Color(0xFFF68B8B))
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Do you really want to logout?",
                    textAlign = TextAlign.Center
                )
            }


        })


}

@Preview(showBackground = true)
@Composable
fun PreviewAlert(modifier: Modifier = Modifier) {
    ShoppingAlertDialog(onCancel = { /*TODO*/ }) {

    }
}

