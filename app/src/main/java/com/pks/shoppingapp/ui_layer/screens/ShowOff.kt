package com.pks.shoppingapp.ui_layer.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.ui_layer.navigation.navItemList

@Composable
fun ShowOff(modifier: Modifier = Modifier.fillMaxSize(),nav:NavHostController,auth: FirebaseAuth) {

    val selectedScreen = remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(

                contentPadding = BottomAppBarDefaults.ContentPadding.apply {
                    PaddingValues(0.dp)
                },

                modifier = Modifier.height(80.dp)
            ) {

                navItemList.forEachIndexed { index, navigationItem ->

                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFF68B8B)
                        ),
                        selected = selectedScreen.intValue == index,
                        onClick = {
                            selectedScreen.intValue = index

                        },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = "")
                        })
                }

            }
        }
    ) {
        inPad->
        inPad
        Box(modifier = Modifier
            .fillMaxSize()
        ){

            when(selectedScreen.intValue){
                0 -> HomeScreenUi(nav = nav)
                1 -> WishListScreenUI(nav = nav)
                2-> CartScreenUI()
                3 -> ProfileScreeUI(auth = auth, nav = nav)
            }
        }

    }
}