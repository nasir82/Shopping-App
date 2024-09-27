package com.pks.shoppingapp.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingapp.authentication.presentation.login.LoginScreenUi
import com.pks.shoppingapp.authentication.presentation.profile.ProfileScreeUI
import com.pks.shoppingapp.authentication.presentation.signup.SignUpScreenUI
import com.pks.shoppingapp.home.presentation.HomeScreenUi
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.ui_layer.screens.ALlCategoryScreenUi
import com.pks.shoppingapp.ui_layer.screens.AllProductScreen
import com.pks.shoppingapp.ui_layer.screens.CartScreenUI
import com.pks.shoppingapp.ui_layer.screens.DetailsScreen
import com.pks.shoppingapp.ui_layer.screens.ShowOff
import com.pks.shoppingapp.ui_layer.screens.WishListScreenUI

@Composable
fun AppNav(firebaseAuth: FirebaseAuth,homeViewModel: HomeViewModel) {
    val nav = rememberNavController()
    val authenticationViewModel:AuthenticationViewModel = hiltViewModel()
    val startDestination =
        if (firebaseAuth.currentUser != null) SubNav.MainHomeScreen else SubNav.LoginSignUpScreen
    if(firebaseAuth.currentUser != null) authenticationViewModel.getUserByUid(firebaseAuth.currentUser!!.uid)
    NavHost(navController = nav, startDestination = startDestination) {
        navigation<SubNav.LoginSignUpScreen>(
            startDestination = NavDestinations.LoginScreen
        ) {
            composable<NavDestinations.SignUpScreen> {
                SignUpScreenUI(nav = nav, viewModel = authenticationViewModel)
            }
            composable<NavDestinations.LoginScreen> {
                LoginScreenUi(nav = nav, firebaseAuth = firebaseAuth, viewModel = authenticationViewModel)
            }
        }

        navigation<SubNav.MainHomeScreen>(
            startDestination = NavDestinations.ShowOff
        ) {
            composable<NavDestinations.HomeScreen> {
                HomeScreenUi(nav = nav, homeViewmodel = homeViewModel)
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
                ShowOff(nav = nav, auth = firebaseAuth, homeViewModel = homeViewModel)

            }
            composable<NavDestinations.ProductDetailsScreen> {
               DetailsScreen(nav = nav)

            }

        }

    }

}



