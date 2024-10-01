package com.pks.shoppingapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingapp.authentication.presentation.login.LoginScreenUi
import com.pks.shoppingapp.authentication.presentation.profile.ProfileScreeUI
import com.pks.shoppingapp.authentication.presentation.signup.SignUpScreenUI
import com.pks.shoppingapp.cart.presentation.CartScreenUI
import com.pks.shoppingapp.cart.presentation.CartViewModel
import com.pks.shoppingapp.category.presentation.ALlCategoryScreenUi
import com.pks.shoppingapp.home.presentation.HomeScreenUi
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.products.presentation.AllProductScreen
import com.pks.shoppingapp.products.presentation.DetailsScreen
import com.pks.shoppingapp.shopping_app.ShowOff
import com.pks.shoppingapp.wishlist.presentation.WishListScreenUI
import com.pks.shoppingapp.wishlist.presentation.WishListViewModel

@Composable
fun AppNav(firebaseAuth: FirebaseAuth, homeViewModel: HomeViewModel) {
    val nav = rememberNavController()
    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()
    val wishListViewModel: WishListViewModel = hiltViewModel()
    val startDestination =
        if (firebaseAuth.currentUser != null) SubNav.MainHomeScreen else SubNav.LoginSignUpScreen
    if (firebaseAuth.currentUser != null) {
        authenticationViewModel.getUserByUid(firebaseAuth.currentUser!!.uid)
        wishListViewModel.getWishList()
        cartViewModel.getCarts()
    }
    NavHost(navController = nav, startDestination = startDestination) {
        navigation<SubNav.LoginSignUpScreen>(
            startDestination = NavDestinations.LoginScreen
        ) {
            composable<NavDestinations.SignUpScreen> {
                SignUpScreenUI(nav = nav, viewModel = authenticationViewModel)
            }
            composable<NavDestinations.LoginScreen> {
                LoginScreenUi(
                    nav = nav,
                    firebaseAuth = firebaseAuth,
                    viewModel = authenticationViewModel,
                    cartViewModel = cartViewModel,
                    wishListViewModel = wishListViewModel
                )
            }
        }

        navigation<SubNav.MainHomeScreen>(
            startDestination = NavDestinations.ShowOff
        ) {
            composable<NavDestinations.HomeScreen> {
                HomeScreenUi(nav = nav, homeViewmodel = homeViewModel)
            }
            composable<NavDestinations.ProfileScreen> {
                ProfileScreeUI(auth = firebaseAuth, nav = nav, viewModel = authenticationViewModel)
            }
            composable<NavDestinations.WishListScreen> {
                WishListScreenUI(nav = nav, viewModel = homeViewModel, wishListViewModel = wishListViewModel)
            }
            composable<NavDestinations.CartScreen> {
                CartScreenUI(cartViewModel = cartViewModel)
            }
            composable<NavDestinations.AllCategory> {
                ALlCategoryScreenUi(nav = nav, homeViewModel = homeViewModel)
            }
            composable<NavDestinations.AllProductScreen> {
                AllProductScreen(nav = nav, viewModel = homeViewModel)
            }
            composable<NavDestinations.ShowOff> {
                ShowOff(nav = nav, auth = firebaseAuth, homeViewModel = homeViewModel,authenticationViewModel = authenticationViewModel,wishListViewModel = wishListViewModel, cartViewModel = cartViewModel)

            }
            composable<NavDestinations.ProductDetailsScreen>(
            ) {
                val productId = it.toRoute<NavDestinations.ProductDetailsScreen>().productId
                DetailsScreen(nav = nav,productId=productId, viewModel = homeViewModel,wishListViewModel = wishListViewModel, cartViewModel = cartViewModel)

            }

        }

    }

}



