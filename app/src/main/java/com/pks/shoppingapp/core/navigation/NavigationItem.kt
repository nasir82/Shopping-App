package com.pks.shoppingapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title:String,
    val icon:ImageVector
)


val navItemList = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home
    ),
     NavigationItem(
        title = "WishList",
        icon = Icons.Default.FavoriteBorder
    ),
     NavigationItem(
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    ),
     NavigationItem(
        title = "Profile",
        icon = Icons.Default.Person
    ),

)
