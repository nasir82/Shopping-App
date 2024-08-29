package com.pks.shoppingappadmin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingTextField(
    modifier: Modifier = Modifier,
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    value: String,
    isEnable:Boolean = true,
    readOnly:Boolean = false,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
    visualTransformation: Boolean = false,
    onValueChange: (String) -> Unit
) {

    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value, onValueChange = {
            onValueChange(it)
        }, label = {
            Text(text = label, modifier = Modifier.padding(start = 0.dp))
        }, modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        enabled = isEnable,
        trailingIcon =trailingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onTrailingClick)
                )
            }
        },
        readOnly = readOnly,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onLeadingClick)
                )
            }
        },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(

            disabledTextColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent


        ),
        visualTransformation = if (visualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardActions = KeyboardActions(
            onDone = {
                localFocusManager.clearFocus()

            },
            onNext = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}

@Composable
fun ShoppingTextField2(
    modifier: Modifier = Modifier,
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    value: String,
    isEnable:Boolean = true,
    readOnly:Boolean = false,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
    visualTransformation: Boolean = false,
    onValueChange: (String) -> Unit
) {

    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value, onValueChange = {
            onValueChange(it)
        }, placeholder = {
            Text(text = label, modifier = Modifier.padding(start = 0.dp))
        }, modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        enabled = isEnable,
        trailingIcon =trailingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onTrailingClick)
                )
            }
        },
        readOnly = readOnly,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onLeadingClick)
                )
            }
        },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(

            disabledTextColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent


        ),
        visualTransformation = if (visualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardActions = KeyboardActions(
            onDone = {
                localFocusManager.clearFocus()

            },
            onNext = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}


@Composable
fun ShoppingSearchBar(
    modifier: Modifier = Modifier,
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    value: String,
    isEnable:Boolean = true,
    readOnly:Boolean = false,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
    visualTransformation: Boolean = false,
    onValueChange: (String) -> Unit
) {

    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value, onValueChange = {
            onValueChange(it)
        }, label = {
            Text(text = label, modifier = Modifier.padding(start = 0.dp))
        }, modifier = modifier
            .fillMaxWidth()
            .padding(0.dp),
        shape = RoundedCornerShape(15.dp),
        enabled = isEnable,
        trailingIcon =trailingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onTrailingClick)
                )
            }
        },
        readOnly = readOnly,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onLeadingClick)
                )
            }
        },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(

            disabledTextColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent


        ),
        visualTransformation = if (visualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardActions = KeyboardActions(
            onDone = {
                localFocusManager.clearFocus()

            },
            onNext = {
                localFocusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearch(){
    ShoppingSearchBar(label = "sundor", value = "This is ") {
        
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BasicText(modifier: Modifier = Modifier.fillMaxWidth()) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = "value",
        onValueChange = {},
        modifier = modifier.clip(shape = RoundedCornerShape(10.dp)).height(30.dp),
        interactionSource = interactionSource,
        enabled = true,
        singleLine = true,
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = "value",
            innerTextField = innerTextField,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            interactionSource = interactionSource,
            contentPadding = PaddingValues(0.dp), // this is how you can remove the padding
        )
    }
}
