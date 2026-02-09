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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.R
import com.pks.shoppingapp.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingapp.authentication.presentation.signup.ShoppingAlertDialog
import com.pks.shoppingapp.core.presentation.components.ShoppingButton
import com.pks.shoppingapp.core.presentation.components.ShoppingTextField2
import com.pks.shoppingapp.core.navigation.NavDestinations

@Composable
fun ProfileScreeUI(
    viewModel: AuthenticationViewModel,
    nav: NavHostController,
    auth: FirebaseAuth
) {
    val state by viewModel.userDataState.collectAsState()
    val focusManager = LocalFocusManager.current

    // UI State
    val isEnable = remember { mutableStateOf(false) }
    val isLogoutDialogOpen = remember { mutableStateOf(false) }

    // Form State (Derived from state or initialized empty)
    val fName = remember { mutableStateOf("") }
    val lName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("No address saved") }

    // Sync form with data once loaded
    LaunchedEffect(state.userData) {
        state.userData?.let { data ->
            val nameParts = data.name.split(" ")
            fName.value = nameParts.firstOrNull() ?: ""
            lName.value = nameParts.getOrNull(1) ?: ""
            email.value = data.email
            phone.value = data.password // Assuming password field holds phone or similar
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
        // Decorative Background Elements
        BackgroundDecorations()

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Image Section
                Box(contentAlignment = Alignment.BottomEnd) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(
                                width = 3.dp,
                                color = if (isEnable.value) Color(0xFFF68B8B) else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Input Fields Group
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        ShoppingTextField2(
                            label = "First Name",
                            isEnable = isEnable.value,
                            value = fName.value,
                            modifier = Modifier.weight(1f)
                        ) { fName.value = it }

                        ShoppingTextField2(
                            label = "Last Name",
                            isEnable = isEnable.value,
                            value = lName.value,
                            modifier = Modifier.weight(1f)
                        ) { lName.value = it }
                    }

                    ShoppingTextField2(
                        label = "Email Address",
                        value = email.value,
                        isEnable = isEnable.value,
                        modifier = Modifier.fillMaxWidth()
                    ) { email.value = it }

                    ShoppingTextField2(
                        label = "Phone Number",
                        value = phone.value,
                        isEnable = isEnable.value,
                        modifier = Modifier.fillMaxWidth()
                    ) { phone.value = it }

                    ShoppingTextField2(
                        label = "Default Address",
                        isEnable = isEnable.value,
                        value = address.value,
                        modifier = Modifier.fillMaxWidth()
                    ) { address.value = it }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Action Buttons
                val btnText = if (isEnable.value) "Save Changes" else "Edit Profile"

                ShoppingButton(
                    text = btnText,
                    containerColor = if (isEnable.value) Color(0xFF4CAF50) else Color(0xFFD9D9D9),
                    contentColor = if (isEnable.value) Color.White else Color.Black
                ) {
                    if (isEnable.value) {
                        // Implement Save Logic: viewModel.updateUser(...)
                        focusManager.clearFocus()
                    }
                    isEnable.value = !isEnable.value
                }

                Spacer(modifier = Modifier.height(12.dp))

                ShoppingButton(
                    text = "Logout",
                    containerColor = Color(0xFFF68B8B).copy(alpha = 0.9f)
                ) {
                    isLogoutDialogOpen.value = true
                }
            }
        }

        // Dialogs
        if (isLogoutDialogOpen.value) {
            ShoppingAlertDialog(
                onCancel = { isLogoutDialogOpen.value = false }
            ) {
                auth.signOut()
                nav.navigate(NavDestinations.LoginScreen) {
                    popUpTo(0) // Clear backstack on logout
                }
            }
        }
    }
}

@Composable
fun BackgroundDecorations() {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .offset(x = (screenWidth * 0.7).dp, y = (-100).dp)
                .size(300.dp)
                .background(Color(0xFFF68B8B).copy(alpha = 0.2f), CircleShape)
        )
        Box(
            modifier = Modifier
                .offset(x = (-100).dp, y = (screenHeight * 0.85).dp)
                .size(250.dp)
                .background(Color(0xFFF68B8B).copy(alpha = 0.2f), CircleShape)
        )
    }
}

//
//@Composable
//fun ProfileScreeUI(
//    viewModel: AuthenticationViewModel,
//    nav:NavHostController,
//    auth:FirebaseAuth
//) {
//
//    val x = LocalConfiguration.current.screenWidthDp - 150
//    val y = LocalConfiguration.current.screenHeightDp - 80
//
//    val state = viewModel.userDataState.collectAsState()
//    if (state.value.isLoading) {
//
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//    } else if (state.value.error.isNotEmpty()) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(text = state.value.error)
//        }
//    } else {
//        val nameParts = remember {
//            state.value.userData?.name?.let {
//                it.split(" ")
//            } ?: listOf("", "")
//        }
//
//        val fName = remember { mutableStateOf(nameParts.first()) }
//        val lName = remember { mutableStateOf(nameParts.getOrNull(1) ?: "") }
//        val email = remember {
//            state.value.userData?.email?.let {
//                mutableStateOf(it)
//            }?: mutableStateOf("")
//        }
//        val password = remember {
//            state.value.userData?.password?.let {
//                mutableStateOf(it)
//            }?: mutableStateOf("")
//        }
//        val confirmPassword = remember {
//            mutableStateOf("No address")
//        }
//        val isEnable = remember {
//            mutableStateOf(false)
//        }
//        val isAnimate = remember {
//            mutableStateOf(false)
//        }
//        val btnText = if (isEnable.value) "Save" else "Edit Profile"
//        val focusManager = LocalFocusManager.current
//        Box(modifier = Modifier.fillMaxSize()) {
//            Box(
//                modifier = Modifier
//
//                    .offset(x = (x).dp, y = (-200).dp)
//                    .background(color = Color(0xFFF68B8B), shape = CircleShape)
//                    .size(size = 350.dp)
//                    .clip(
//                        shape = CircleShape
//                    )
//            )
//            Box(
//                modifier = Modifier
//
//                    .offset(x = (-160).dp, y = (y).dp)
//                    .background(color = Color(0xFFF68B8B), shape = CircleShape)
//                    .size(size = 250.dp)
//                    .clip(
//                        shape = CircleShape
//                    )
//            )
//
//            Column(
//                Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 16.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.Center
//            ) {
//
//                Image(
//                    painterResource(id = R.drawable.profile),
//                    contentScale = ContentScale.Crop,
//                    contentDescription = "",
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(shape = CircleShape)
//                        .border(width = 2.dp, color = Color(0xFFF68B8B), shape = CircleShape)
//                )
//                HrSpacer(height = 20)
//
//                Row(modifier = Modifier.fillMaxWidth()) {
//                    ShoppingTextField2(
//                        label = "First Name",
//                        isEnable = isEnable.value,
//                        value = fName.value,
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        fName.value = it
//                    }
//                    Spacer(modifier = Modifier.width(10.dp))
//                    ShoppingTextField2(
//                        label = "Last Name",
//                        isEnable = isEnable.value,
//                        value = lName.value,
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        lName.value = it
//                    }
//
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                ShoppingTextField2(label = "Email", value = email.value, isEnable = isEnable.value) {
//                    email.value = it
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                ShoppingTextField2(
//                    label = "Phone",
//                    value = password.value,
//                    isEnable = isEnable.value
//                ) {
//                    password.value = it
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                ShoppingTextField2(
//                    label = "Address",
//                    isEnable = isEnable.value,
//                    value = confirmPassword.value
//                ) {
//                    confirmPassword.value = it
//                }
//
//                if(isAnimate.value) {
//                        ShoppingAlertDialog(onCancel = { isAnimate.value = !isAnimate.value }) {
//                            auth.signOut().let {
//                                nav.navigate(NavDestinations.LoginScreen)
//                                isAnimate.value = !isAnimate.value
//                            }
//
//                        }
//                }
//
//                Spacer(modifier = Modifier.height(20.dp))
//                ShoppingButton(text = "Logout", containerColor = Color(0xFFF68B8B)) {
//                   // nav.navigate(NavDestinations.LogOut)
//                    isAnimate.value = !isAnimate.value
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//                ShoppingButton(text = btnText, containerColor = Color(0xFFD9D9D9)) {
//                    focusManager.clearFocus()
//                    isEnable.value = !isEnable.value
//                }
//
//
//            }
//
//        }
//    }
//}
//
//
