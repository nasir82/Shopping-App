package com.pks.shoppingapp.authentication.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.R
import com.pks.shoppingapp.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingapp.authentication.presentation.signup.HrSpacer
import com.pks.shoppingapp.authentication.presentation.signup.ShoppingAlertDialog
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.components.ShoppingTextField
import com.pks.shoppingapp.components.ShoppingTextField2
import com.pks.shoppingapp.navigation.NavDestinations

@Composable
fun ProfileScreeUI(
    viewModel: AuthenticationViewModel,
    nav:NavHostController,
    auth:FirebaseAuth
) {

    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80

    val state = viewModel.profileScreenState.collectAsState()
    if (state.value.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.value.errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.value.errorMessage.toString())
        }
    } else {
        val nameParts = remember {
            state.value.userData?.name?.let {
                it.split(" ")
            } ?: listOf("", "")
        }

        val fName = remember { mutableStateOf(nameParts.first()) }
        val lName = remember { mutableStateOf(nameParts.getOrNull(1) ?: "") }
        val email = remember {
            state.value.userData?.email?.let {
                mutableStateOf(it)
            }?: mutableStateOf("")
        }
        val password = remember {
            state.value.userData?.password?.let {
                mutableStateOf(it)
            }?: mutableStateOf("")
        }
        val confirmPassword = remember {
            mutableStateOf("No address")
        }
        val isEnable = remember {
            mutableStateOf(false)
        }
        val isAnimate = remember {
            mutableStateOf(false)
        }
        val btnText = if (isEnable.value) "Save" else "Edit Profile"
        val focusManager = LocalFocusManager.current
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier

                    .offset(x = (x).dp, y = (-200).dp)
                    .background(color = Color(0xFFF68B8B), shape = CircleShape)
                    .size(size = 350.dp)
                    .clip(
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier

                    .offset(x = (-160).dp, y = (y).dp)
                    .background(color = Color(0xFFF68B8B), shape = CircleShape)
                    .size(size = 250.dp)
                    .clip(
                        shape = CircleShape
                    )
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painterResource(id = R.drawable.profile),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = CircleShape)
                        .border(width = 2.dp, color = Color(0xFFF68B8B), shape = CircleShape)
                )
                HrSpacer(height = 20)

                Row(modifier = Modifier.fillMaxWidth()) {
                    ShoppingTextField2(
                        label = "First Name",
                        isEnable = isEnable.value,
                        value = fName.value,
                        modifier = Modifier.weight(1f)
                    ) {
                        fName.value = it
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    ShoppingTextField2(
                        label = "Last Name",
                        isEnable = isEnable.value,
                        value = lName.value,
                        modifier = Modifier.weight(1f)
                    ) {
                        lName.value = it
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                ShoppingTextField2(label = "Email", value = email.value, isEnable = isEnable.value) {
                    email.value = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                ShoppingTextField2(
                    label = "Phone",
                    value = password.value,
                    isEnable = isEnable.value
                ) {
                    password.value = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                ShoppingTextField2(
                    label = "Address",
                    isEnable = isEnable.value,
                    value = confirmPassword.value
                ) {
                    confirmPassword.value = it
                }

                if(isAnimate.value) {
                        ShoppingAlertDialog(onCancel = { isAnimate.value = !isAnimate.value }) {
                            auth.signOut().let {
                                nav.navigate(NavDestinations.LoginScreen)
                                isAnimate.value = !isAnimate.value
                            }

                        }
                }

                Spacer(modifier = Modifier.height(20.dp))
                ShoppingButton(text = "Logout", containerColor = Color(0xFFF68B8B)) {
                   // nav.navigate(NavDestinations.LogOut)
                    isAnimate.value = !isAnimate.value
                }

                Spacer(modifier = Modifier.height(24.dp))
                ShoppingButton(text = btnText, containerColor = Color(0xFFD9D9D9)) {
                    focusManager.clearFocus()
                    isEnable.value = !isEnable.value
                }


            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun forPRev(modifier: Modifier = Modifier) {
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
    val password = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
        mutableStateOf("")
    }

    val isShow = remember {
        mutableStateOf(true)
    }

    val isEnable = remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier

                .offset(x = (x).dp, y = (-200).dp)
                .background(color = Color(0xFFF68B8B), shape = CircleShape)
                .size(size = 350.dp)
                .clip(
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier

                .offset(x = (-160).dp, y = (y).dp)
                .background(color = Color(0xFFF68B8B), shape = CircleShape)
                .size(size = 250.dp)
                .clip(
                    shape = CircleShape
                )
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painterResource(id = R.drawable.profile),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
            )
            HrSpacer(height = 20)

            Row(modifier = Modifier.fillMaxWidth()) {
                ShoppingTextField(
                    label = "First Name",
                    isEnable = isEnable.value,
                    value = fName.value,
                    modifier = Modifier.weight(1f)
                ) {
                    fName.value = it
                }
                Spacer(modifier = Modifier.width(10.dp))
                ShoppingTextField(
                    label = "Last Name",
                    isEnable = isEnable.value,
                    value = lName.value,
                    modifier = Modifier.weight(1f)
                ) {
                    lName.value = it
                }

            }

            ShoppingTextField(label = "Email", value = email.value, isEnable = isEnable.value) {
                email.value = it
            }
            ShoppingTextField(
                label = "password",
                value = password.value,
                isEnable = isEnable.value,
                visualTransformation = isShow.value,
                trailingIcon = Icons.Default.Person,
                onTrailingClick = {
                    isShow.value = !isShow.value
                }) {
                password.value = it
            }
            ShoppingTextField(
                label = "confirm password",
                visualTransformation = isShow.value,
                isEnable = isEnable.value,
                value = confirmPassword.value,
                trailingIcon = Icons.Default.Person,
                onTrailingClick = {
                    isShow.value = !isShow.value
                }) {
                confirmPassword.value = it
            }

            Spacer(modifier = Modifier.height(20.dp))
            ShoppingButton(text = "Logout", containerColor = Color(0xFFF68B8B)) {

            }

            Spacer(modifier = Modifier.height(24.dp))
//            ShoppingButton(text = "Edit Profile", containerColor =Color(0xFFF68B8B)) {
//            }

            ShoppingButton(text = "Edit Profile", containerColor = Color(0xFFF68B8B)) {

            }


        }

    }
}


