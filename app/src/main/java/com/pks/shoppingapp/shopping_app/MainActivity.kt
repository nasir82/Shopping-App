package com.pks.shoppingapp.shopping_app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.home.presentation.HomeViewModel
import com.pks.shoppingapp.navigation.AppNav
import com.pks.shoppingapp.ui.theme.ShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme {
                /**
                 * Fetch homepage product using home viewmodel initialization
                 */
                val homeViewModel:HomeViewModel = hiltViewModel()
                AppNav(firebaseAuth = firebaseAuth,homeViewModel=homeViewModel)
            }
        }
    }
}

