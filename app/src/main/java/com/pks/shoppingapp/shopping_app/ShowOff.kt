package com.pks.shoppingapp.shopping_app

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
import com.pks.shoppingapp.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingapp.authentication.presentation.profile.SettingScreenUI
import com.pks.shoppingapp.cart.presentation.CartScreenUI
import com.pks.shoppingapp.cart.presentation.CartViewModel
import com.pks.shoppingapp.home.presentation.HomeScreenUi
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.core.navigation.navItemList
import com.pks.shoppingapp.products.presentation.DetailsViewModel
import com.pks.shoppingapp.wishlist.presentation.WishListScreenUI
import com.pks.shoppingapp.wishlist.presentation.WishListViewModel
import com.pks.shoppingapp.wishlist.utils.DataStoreViewModel

@Composable
fun ShowOff(
    nav: NavHostController,
    auth: FirebaseAuth,
    homeViewModel: HomeViewModel,
    authenticationViewModel: AuthenticationViewModel,
    wishListViewModel: WishListViewModel,
    cartViewModel: CartViewModel,
    detailsViewModel: DetailsViewModel,
    dataStoreViewModel: DataStoreViewModel
) {

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
    ) { inPad ->
        inPad
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            when (selectedScreen.intValue) {
                0 -> HomeScreenUi(
                    nav = nav,
                    homeViewmodel = homeViewModel,
                    detailsViewModel = detailsViewModel,
                    dataStoreViewModel
                )

                1 -> WishListScreenUI(
                    nav = nav,
                    detailsViewModel = detailsViewModel
                )

                2 -> CartScreenUI(nav,cartViewModel = cartViewModel)
                3 -> SettingScreenUI(nav = nav, authenticationViewModel = authenticationViewModel)
            }
        }

    }
}