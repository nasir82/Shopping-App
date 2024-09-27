package com.pks.shoppingapp.ui_layer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BorderStyle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.R
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.ui_layer.navigation.NavDestinations


@Preview(showBackground = true)
@Composable
fun SettingScreenUI(modifier: Modifier = Modifier,nav:NavHostController = rememberNavController()) {

    val height = LocalConfiguration.current.screenHeightDp+150
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(color = Color.Blue)){
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Account")
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {

                    }, verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painterResource(id = R.drawable.profile),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier
                    .weight(1f)
                    .clickable {
                        FirebaseAuth.getInstance().signOut()
                        nav.navigate(NavDestinations.LoginScreen)
                    }) {
                    Text(text = "name")
                    Text(text = "Yamin")
                }
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Column (modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .verticalScroll(rememberScrollState())
            .clip(shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp))
            .background(color = Color.White)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)) {

                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Account Setting")
                Spacer(modifier = Modifier.height(16.dp))

                AccountItems()
                Spacer(modifier = Modifier.height(12.dp))
                repeat(6){
                    AccountItems()
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Text(text = "App Settings")
                Spacer(modifier = Modifier.height(24.dp))

                repeat(6){
                    AccountItems(icon = true)
                    Spacer(modifier = Modifier.height(12.dp))
                }



//                ShoppingButton(text = "Clear Account") {
//
//                }

                ShoppingButton(text = "Close Account") {

                }

                Spacer(modifier = Modifier.height(90.dp))





            }

        }

    }

}


@Composable
fun AccountItems(modifier: Modifier = Modifier.fillMaxWidth(),icon:Boolean = false) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.BorderStyle, contentDescription = "",modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Title")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Subtitle")
        }

        if(icon) Icon(imageVector = Icons.Default.ToggleOn, contentDescription = "",modifier = Modifier.size(18.dp))
    }
}