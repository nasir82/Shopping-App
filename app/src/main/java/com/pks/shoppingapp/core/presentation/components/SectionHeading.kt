package com.pks.shoppingapp.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pks.shoppingapp.checkout.PaymentModel
import com.pks.shoppingapp.checkout.PaymentViewModel
import com.pks.shoppingapp.personalization.address.domain.model.AddressModel
import com.pks.shoppingapp.personalization.address.presentation.AddressViewModel

@Composable
fun SectionHeading(title: String, text: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        TextButton(onClick = {
            onClick.invoke()
        }) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

    }
}

@Composable
fun SectionHeadingWithComposable(
    title: String,
    text: String,
    onClick: (AddressModel) -> Unit = {}
) {
    val addressviewModel: AddressViewModel = hiltViewModel()
    val addresses = addressviewModel.addresses
    val expanded = remember {
        mutableStateOf(false)
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            TextButton(onClick = {
                expanded.value = !expanded.value
            }) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }


        }

        AnimatedVisibility(visible = expanded.value) {
            LazyColumn(
                modifier = Modifier
                    .heightIn(min = 280.dp, max = 300.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy((8.dp))
            ) {

                items(addresses) { currentAddress ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .clickable {
                                    onClick.invoke(currentAddress)
                                    expanded.value = !expanded.value
                                },
                            verticalArrangement = Arrangement.spacedBy((8.dp))
                        ) {
                            Text(
                                text = currentAddress.name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = currentAddress.phoneNumber,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = currentAddress.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
                item {
                    Column {
                        ShoppingButton(text = "Add New Address") {

                        } 
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    
                }
               
            }
        }
    }
}


@Composable
fun ChangePaymentMethod(
    title: String,
    text: String,
    onClick: (PaymentModel) -> Unit = {}
) {
    val addressviewModel: PaymentViewModel = hiltViewModel()
    val addresses = addressviewModel.paymentMethods
    val expanded = remember {
        mutableStateOf(false)
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            TextButton(onClick = {
                expanded.value = !expanded.value
            }) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }


        }

        AnimatedVisibility(visible = expanded.value) {
            LazyColumn(
                modifier = Modifier
                    .heightIn(min = 280.dp, max = 300.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy((8.dp))
            ) {

                items(addresses) { currentMethod ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .clickable {
                                    onClick.invoke(currentMethod)
                                    expanded.value = !expanded.value
                                },
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Payment, contentDescription = "")
                            Text(
                                text = currentMethod.name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                        }
                    }
                }

            }
        }
    }
}