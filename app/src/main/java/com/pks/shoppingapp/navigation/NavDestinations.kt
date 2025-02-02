package com.pks.shoppingapp.navigation

import kotlinx.serialization.Serializable


sealed class SubNav {
    @Serializable
    data object LoginSignUpScreen : SubNav()

    @Serializable
    data object MainHomeScreen : SubNav()

}

sealed class NavDestinations {
    @Serializable
    data object LoginScreen : NavDestinations()

    @Serializable

    data object SignUpScreen : NavDestinations()

    @Serializable
    data object ProfileScreen : NavDestinations()

    @Serializable
    data object HomeScreen : NavDestinations()

    @Serializable
    data object WishListScreen : NavDestinations()

    @Serializable
    data object CartScreen : NavDestinations()
    @Serializable
    data object CheckOut : NavDestinations()
    @Serializable
    data object PaymentGateway : NavDestinations()

    @Serializable
    data class ProductDetailsScreen(val productId:String) : NavDestinations()

    @Serializable
    data object AllProductScreen : NavDestinations()

    @Serializable
    data object LogOut : NavDestinations()

    @Serializable
    data object AllCategory : NavDestinations()

    @Serializable
    data object Orders : NavDestinations()
    @Serializable
    data object Gemini : NavDestinations()


    @Serializable
    data object ShowOff : NavDestinations()

    @Serializable
    data class CategoryBasedProducts(val categoryName: String) : NavDestinations()

    @Serializable
    data object AddressScreen : NavDestinations()


}