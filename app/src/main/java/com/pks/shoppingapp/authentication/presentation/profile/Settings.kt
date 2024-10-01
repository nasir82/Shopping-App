package com.pks.shoppingapp.authentication.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingapp.R
import com.pks.shoppingapp.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingapp.components.ShoppingButton
import com.pks.shoppingapp.navigation.NavDestinations


@Composable
fun SettingScreenUI(
    modifier: Modifier = Modifier,
    nav: NavHostController,
    authenticationViewModel: AuthenticationViewModel
) {

    val height = LocalConfiguration.current.screenHeightDp + 150
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
    ) {

        val state = authenticationViewModel.userDataState.collectAsState().value
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Account", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent), verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painterResource(id = R.drawable.profile),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier
                    .weight(1f)
                    .background(color = Color.Transparent)
                    ) {
                    Text(text = state.userData.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = state.userData.email, style = MaterialTheme.typography.bodyMedium)
                }
                Icon(imageVector = Icons.Default.Edit, contentDescription = "", modifier = Modifier.clickable {
                    FirebaseAuth
                        .getInstance()
                        .signOut()
                    nav.navigate(NavDestinations.LoginScreen){
                        nav.popBackStack()
                    }
                })
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp)
                .verticalScroll(rememberScrollState())
                .clip(shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp))
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Account Setting",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))

                AccountItems(
                    title = "Address",
                    subtitle = "",
                    imageVector = Icons.Default.LocationOn
                )
                Spacer(modifier = Modifier.height(12.dp))
                AccountItems(
                    title = "My Order",
                    subtitle = "",
                    imageVector = Icons.Default.ShoppingBag
                )
                Spacer(modifier = Modifier.height(12.dp))
                AccountItems(
                    title = "Bank Account",
                    subtitle = "",
                    imageVector = Icons.Default.CreditCard
                )
                Spacer(modifier = Modifier.height(12.dp))
                AccountItems(
                    title = "Notification",
                    subtitle = "",
                    imageVector = Icons.Default.Notifications
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "App Settings",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(24.dp))
                AccountItems(
                    title = "Privacy",
                    subtitle = "",
                    imageVector = Icons.Default.PrivacyTip
                )
                Spacer(modifier = Modifier.height(12.dp))
                AccountItems(
                    title = "About",
                    subtitle = "",
                    imageVector = Icons.Default.Info
                )
                Spacer(modifier = Modifier.height(12.dp))
                ShoppingButton(text = "Close Account", containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onBackground) {

                }

                Spacer(modifier = Modifier.height(90.dp))


            }

        }

    }

}


@Composable
fun AccountItems(
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: Boolean = false,
    title: String,
    subtitle: String,
    imageVector: ImageVector
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Default.KeyboardDoubleArrowRight,
            contentDescription = "",
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}