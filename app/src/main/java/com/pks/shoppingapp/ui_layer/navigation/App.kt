package com.pks.shoppingapp.ui_layer.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.ui_layer.screens.ALlCategoryScreenUi
import com.pks.shoppingapp.ui_layer.screens.AllProductScreen
import com.pks.shoppingapp.ui_layer.screens.CartScreenUI
import com.pks.shoppingapp.ui_layer.screens.DetailsScreen
import com.pks.shoppingapp.ui_layer.screens.HomeScreenUi
import com.pks.shoppingapp.ui_layer.screens.LoginScreenUi
import com.pks.shoppingapp.ui_layer.screens.ProfileScreeUI
import com.pks.shoppingapp.ui_layer.screens.ShowOff
import com.pks.shoppingapp.ui_layer.screens.SignUpScreenUI
import com.pks.shoppingapp.ui_layer.screens.WishListScreenUI

@Composable
fun App(modifier: Modifier = Modifier.fillMaxSize(), firebaseAuth: FirebaseAuth,nav:NavHostController) {


    val selectedScreen = remember {
        mutableIntStateOf(0)
    }
    var selectedItem by remember { mutableIntStateOf(0) }
    val navBackStackEntry by nav.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val startDestination =
        if (firebaseAuth.currentUser != null) SubNav.MainHomeScreen else SubNav.LoginSignUpScreen
    Box(modifier = modifier) {


        NavHost(navController = nav, startDestination = startDestination) {
            navigation<SubNav.LoginSignUpScreen>(
                startDestination = NavDestinations.LoginScreen
            ) {
                composable<NavDestinations.SignUpScreen> {
                    SignUpScreenUI(nav = nav)
                    //HomeScreenUi()
                    //SuccessScreen()
                }
                composable<NavDestinations.LoginScreen> {
                    LoginScreenUi(nav = nav, firebaseAuth = firebaseAuth)
                }


            }

            navigation<SubNav.MainHomeScreen>(
                startDestination = NavDestinations.ShowOff
            ) {
                composable<NavDestinations.HomeScreen> {
                    HomeScreenUi(nav = nav)
                }
                composable<NavDestinations.ProfileScreen> {
                    ProfileScreeUI(auth = firebaseAuth, nav = nav)
                }
                composable<NavDestinations.WishListScreen> {
                    WishListScreenUI()
                }
                composable<NavDestinations.CartScreen> {
                    CartScreenUI()
                }
                composable<NavDestinations.AllCategory> {
                    ALlCategoryScreenUi(nav= nav)
                }
                composable<NavDestinations.AllProductScreen> {
                    AllProductScreen(nav= nav)
                }
                composable<NavDestinations.ShowOff> {
                    //HomeScreenUi()
                    //HomeScreenUi()
                    //SuccessScreen()
                    ShowOff(nav = nav,auth = firebaseAuth)

                }

            }

        }
    }
}


@Composable
fun AppNav(firebaseAuth: FirebaseAuth) {
    val nav = rememberNavController()
    val startDestination =
        if (firebaseAuth.currentUser != null) SubNav.MainHomeScreen else SubNav.LoginSignUpScreen
    NavHost(navController = nav, startDestination = startDestination) {
        navigation<SubNav.LoginSignUpScreen>(
            startDestination = NavDestinations.LoginScreen
        ) {
            composable<NavDestinations.SignUpScreen> {
                SignUpScreenUI(nav = nav)
            }
            composable<NavDestinations.LoginScreen> {
                LoginScreenUi(nav = nav, firebaseAuth = firebaseAuth)
            }
        }

        navigation<SubNav.MainHomeScreen>(
            startDestination = NavDestinations.ShowOff
        ) {
            composable<NavDestinations.HomeScreen> {
                HomeScreenUi(nav = nav)
            }
            composable<NavDestinations.ProfileScreen> {
                ProfileScreeUI(auth = firebaseAuth, nav = nav)
            }
            composable<NavDestinations.WishListScreen> {
                WishListScreenUI()
            }
            composable<NavDestinations.CartScreen> {
                CartScreenUI()
            }
            composable<NavDestinations.AllCategory> {
                ALlCategoryScreenUi(nav= nav)
            }
            composable<NavDestinations.AllProductScreen> {
                AllProductScreen(nav = nav)
            }
            composable<NavDestinations.ShowOff> {
                ShowOff(nav = nav, auth = firebaseAuth)

            }
            composable<NavDestinations.ProductDetailsScreen> {
               DetailsScreen(nav = nav)

            }

        }

    }

}



