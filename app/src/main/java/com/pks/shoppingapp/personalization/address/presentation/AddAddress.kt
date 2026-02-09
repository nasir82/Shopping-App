package com.pks.shoppingapp.personalization.address.presentation

import android.R.attr.progress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingapp.personalization.address.domain.model.AddressModel
import com.pks.shoppingapp.personalization.address.presentation.components.AddressTextField
import kotlinx.coroutines.launch


@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun AddAddressScreen(
    viewModel: AddressViewModel,
    nav: NavHostController
) {
    val state by viewModel.addAddressState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.isSuccess) {
        print("inside launch effect")
        if (state.isSuccess) {
            scope.launch {
                snackbarHostState.showSnackbar("Address added successfully")
            }
            nav.popBackStack()
            viewModel.resetAddAddressState()
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { error ->
            scope.launch {
                snackbarHostState.showSnackbar(error)
            }
            viewModel.resetAddAddressState()
        }
    }

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var stateProvince by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Address") },
                navigationIcon = {
                    IconButton(onClick = {nav.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AddressTextField(value = name, onValueChange = { name = it }, label = "Full Name")
            AddressTextField(value = phone, onValueChange = { phone = it }, label = "Phone Number")
            AddressTextField(value = street, onValueChange = { street = it }, label = "Street Address")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AddressTextField(modifier = Modifier.weight(1f), value = city, onValueChange = { city = it }, label = "City")
                AddressTextField(modifier = Modifier.weight(1f), value = stateProvince, onValueChange = { stateProvince = it }, label = "State")
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AddressTextField(modifier = Modifier.weight(1f), value = zipCode, onValueChange = { zipCode = it }, label = "Zip Code")
                AddressTextField(modifier = Modifier.weight(1f), value = country, onValueChange = { country = it }, label = "Country")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newAddress =
                        AddressModel(name, phone, street, zipCode, city, stateProvince, country)
                    viewModel.addUserAddress(newAddress)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && phone.isNotBlank() && !state.isLoading,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                    progress = { progress.toFloat() },
                        color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                    trackColor = ProgressIndicatorDefaults.circularTrackColor,
                    strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                    )
                } else {
                    Text("Save Address")
                }
            }
        }
    }
}
