package com.pks.shoppingapp.authentication.presentation.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pks.shoppingapp.R
import com.pks.shoppingapp.authentication.domain.model.UserData
import com.pks.shoppingapp.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingapp.components.DividerWithText
import com.pks.shoppingapp.components.LoginWithSocialMedia
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.components.ShoppingTextField
import com.pks.shoppingapp.ui_layer.navigation.NavDestinations
import com.pks.shoppingapp.ui_layer.screens.SuccessScreen
import kotlinx.coroutines.launch


@Composable
fun SignUpScreenUI(
    viewModel:AuthenticationViewModel,
    nav: NavController
) {
    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80
    val fName = remember {
        mutableStateOf("")
    }
    val lName = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val address = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
        mutableStateOf("")
    }

    val isShow = remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.signupScreenState.collectAsState()
    if (state.value.isLoading) {

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.value.error != null) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background), verticalArrangement = Arrangement.Center) {
            Text(text = state.value.error.toString(), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            ShoppingButton(text = "Try Again", contentColor = MaterialTheme.colorScheme.onBackground, containerColor = MaterialTheme.colorScheme.primary) {
                viewModel.reAssignSignUp()
            }
        }
    } else if (state.value.userData != null) {
        /**
         * Don't need to collect because there is no such list
         */
        SuccessScreen(navDestinations = nav)
    } else {

        var icon = if (isShow.value) Icons.Default.VisibilityOff else Icons.Default.Visibility
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)) {
            Box(
                modifier = Modifier

                    .offset(x = (x).dp, y = (-200).dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .size(size = 350.dp)
                    .clip(
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier

                    .offset(x = (-160).dp, y = (y).dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .size(size = 250.dp)
                    .clip(
                        shape = CircleShape
                    )
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = "Signup", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                HrSpacer(height = 20)

                Row(modifier = Modifier.fillMaxWidth()) {
                    ShoppingTextField(
                        label = "First Name",
                        value = fName.value,
                        modifier = Modifier.weight(1f)
                    ) {
                        fName.value = it
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    ShoppingTextField(
                        label = "Last Name",
                        value = lName.value,
                        modifier = Modifier.weight(1f)
                    ) {
                        lName.value = it
                    }

                }

                ShoppingTextField(label = "Email", value = email.value) {
                    email.value = it
                }
                ShoppingTextField(label = "Address", value = address.value) {
                    address.value = it
                }
                ShoppingTextField(
                    label = "password",
                    value = password.value,
                    visualTransformation = isShow.value,
                    trailingIcon = icon,
                    onTrailingClick = {
                        isShow.value = !isShow.value
                    }) {
                    password.value = it
                }
                ShoppingTextField(
                    label = "confirm password",
                    visualTransformation = isShow.value,
                    value = confirmPassword.value,
                    trailingIcon = icon,
                    onTrailingClick = {
                        isShow.value = !isShow.value
                    }) {
                    confirmPassword.value = it
                }

                Spacer(modifier = Modifier.height(20.dp))
                ShoppingButton(text = "Signup", containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onBackground) {
                    if (fName.value.isBlank() || email.value.isBlank() || password.value.isBlank() || confirmPassword.value.isBlank()) {
                        Log.d("isBlack","Blank ase")
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "This is a Snackbar for remind to fill",
                                actionLabel = "Undo"
                            )
                        }
                    } else if (password.value != confirmPassword.value) {
                        Toast.makeText(context, "Password don't match", Toast.LENGTH_SHORT).show()
                    } else {
                        val user = UserData(
                            name = "${fName.value} ${lName.value}",
                            email = email.value,
                            password = password.value,
                            phone = "phone"
                        )
                        viewModel.createUser(user)
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Already have an account?  ", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                    Text(
                        text = "Login",
                         style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable {
                            nav.navigate(NavDestinations.LoginScreen)
                        })
                }

                Spacer(modifier = Modifier.height(24.dp))
                DividerWithText(text = "Or signup with")
                Spacer(modifier = Modifier.height(24.dp))
                LoginWithSocialMedia(media = R.drawable.google, text = "Login with google")
                Spacer(modifier = Modifier.height(24.dp))
                LoginWithSocialMedia(media = R.drawable.facebook, text = "Login with facebook")


            }

        }
    }
}

@Composable
fun HrSpacer(modifier: Modifier = Modifier, height: Int) {
    Spacer(modifier = Modifier.height(height.dp))

}