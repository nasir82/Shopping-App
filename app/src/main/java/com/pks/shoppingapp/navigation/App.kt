package com.pks.shoppingapp.navigation

import android.util.Log
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
import com.pks.shoppingapp.checkout.CheckOut
import com.pks.shoppingapp.home.presentation.HomeScreenUi
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.products.presentation.AllProductScreen
import com.pks.shoppingapp.products.presentation.CategoryBasedProduct
import com.pks.shoppingapp.products.presentation.DetailsScreen
import com.pks.shoppingapp.products.presentation.DetailsViewModel
import com.pks.shoppingapp.shopping_app.ShowOff
import com.pks.shoppingapp.wishlist.presentation.WishListScreenUI
import com.pks.shoppingapp.wishlist.presentation.WishListViewModel
import com.pks.shoppingapp.wishlist.utils.DataStoreViewModel

@Composable
fun AppNav(firebaseAuth: FirebaseAuth, homeViewModel: HomeViewModel) {
    val nav = rememberNavController()
    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()
    val wishListViewModel: WishListViewModel = hiltViewModel()
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    val startDestination =
        if (firebaseAuth.currentUser != null) SubNav.MainHomeScreen else SubNav.LoginSignUpScreen
    if (firebaseAuth.currentUser != null) {
        authenticationViewModel.getUserByUid(firebaseAuth.currentUser!!.uid)
        authenticationViewModel.onLoginSuccess(firebaseAuth.currentUser!!.uid)
        Log.d("on init print", " called")
        wishListViewModel.getWishList()
        cartViewModel.getCarts()
    }

    /**
     * here the whole navHost is the same scope so creating variable in this scope will not redundant
     * in the hilt scope similarly it will create just after login okay.
     */

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
                val datastoreViewModel: DataStoreViewModel = hiltViewModel()
                HomeScreenUi(
                    nav = nav,
                    homeViewmodel = homeViewModel,
                    detailsViewModel = detailsViewModel,
                    datastoreViewModel
                )
            }
            composable<NavDestinations.ProfileScreen> {
                ProfileScreeUI(auth = firebaseAuth, nav = nav, viewModel = authenticationViewModel)
            }
            composable<NavDestinations.WishListScreen> {
                WishListScreenUI(
                    nav = nav,
                    viewModel = homeViewModel,
                    wishListViewModel = wishListViewModel,
                    detailsViewModel = detailsViewModel
                )
            }
            composable<NavDestinations.CartScreen> {
                CartScreenUI(nav,cartViewModel = cartViewModel)
            }

            composable<NavDestinations.CheckOut> {
                CheckOut(nav ,cartViewModel = cartViewModel)
            }


            composable<NavDestinations.AllCategory> {
                ALlCategoryScreenUi(nav = nav, homeViewModel = homeViewModel)
            }
            composable<NavDestinations.AllProductScreen> {
                val datastoreViewModel: DataStoreViewModel = hiltViewModel()
                AllProductScreen(
                    nav = nav,
                    viewModel = homeViewModel,
                    detailsViewModel = detailsViewModel,
                    datastoreViewModel
                )
            }
            composable<NavDestinations.ShowOff> {
                val datastoreViewModel: DataStoreViewModel = hiltViewModel()
                ShowOff(
                    nav = nav,
                    auth = firebaseAuth,
                    homeViewModel = homeViewModel,
                    authenticationViewModel = authenticationViewModel,
                    wishListViewModel = wishListViewModel,
                    cartViewModel = cartViewModel,
                    detailsViewModel = detailsViewModel,
                    datastoreViewModel
                )

            }
            composable<NavDestinations.ProductDetailsScreen>(
            ) {
                val productId = it.toRoute<NavDestinations.ProductDetailsScreen>().productId
                DetailsScreen(
                    nav = nav,
                    productId = productId,
                    viewModel = homeViewModel,
                    wishListViewModel = wishListViewModel,
                    cartViewModel = cartViewModel,
                    detailsViewModel = detailsViewModel
                )

            }
            composable<NavDestinations.CategoryBasedProducts>(
            ) {
                val categoryName = it.toRoute<NavDestinations.CategoryBasedProducts>().categoryName
                CategoryBasedProduct(
                    nav = nav,
                    viewModel = homeViewModel,
                    categoryName = categoryName,
                    detailsViewModel = detailsViewModel
                )

            }

        }

    }

}



