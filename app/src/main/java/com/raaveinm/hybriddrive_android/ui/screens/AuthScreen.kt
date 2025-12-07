package com.raaveinm.hybriddrive_android.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.raaveinm.hybriddrive.R
import com.raaveinm.hybriddrive_android.ui.navigation.NavData
import com.raaveinm.hybriddrive_android.ui.viewmodel.AuthScreenViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthScreenViewModel = viewModel()
) {
    if (viewModel.isLoggedIn.value) {
        LaunchedEffect(key1 = Unit) {
            navController.navigate(NavData.Files.route) {
                popUpTo(NavData.Auth.route) { inclusive = true }
            }
        }
    }
    val keyboardManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.auth_welcome),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.normal))
                .align(alignment = Alignment.CenterHorizontally)
        )

        Column {
            OutlinedTextField(
                value = viewModel.username.value,
                onValueChange = { viewModel.username.value = it },
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.extra_small)),
                textStyle = MaterialTheme.typography.bodyMedium,
                label = { Text(stringResource(R.string.login_txt)) },
                trailingIcon = {
                    AnimatedVisibility(visible = viewModel.username.value != "") {
                        IconButton(onClick = { viewModel.username.value = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Unspecified,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    keyboardManager.moveFocus(FocusDirection.Down)
                }),
                singleLine = true,
                maxLines = 1,
            )

            OutlinedTextField(
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.extra_small)),
                textStyle = MaterialTheme.typography.bodyMedium,
                label = { Text(stringResource(R.string.password_txt)) },
                trailingIcon = {
                    AnimatedVisibility(visible = viewModel.password.value != "") {
                        IconButton(onClick = { viewModel.password.value = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Unspecified,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Password,
                    imeAction =
                        if (viewModel.register.value) ImeAction.Next
                        else ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onNext = {
                    if (viewModel.register.value) {
                        keyboardManager.moveFocus(FocusDirection.Down)
                    } else {
                        viewModel.login()
                        keyboardManager.clearFocus()
                    }
                }),
                singleLine = true,
                maxLines = 1,
            )

            AnimatedVisibility(visible = viewModel.register.value) {
                OutlinedTextField(
                    value = viewModel.confirmPassword.value,
                    onValueChange = { viewModel.confirmPassword.value = it },
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.extra_small)),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = { Text(stringResource(R.string.confirm_password_txt)) },
                    trailingIcon = {
                        AnimatedVisibility(visible = viewModel.confirmPassword.value != "") {
                            IconButton(onClick = { viewModel.confirmPassword.value = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Unspecified,
                        autoCorrectEnabled = true,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardManager.clearFocus()
                        viewModel.register()
                    }),
                    singleLine = true,
                    maxLines = 1,
                )
            }
        }


        AnimatedVisibility(visible = !WindowInsets.isImeVisible) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        if (viewModel.register.value) {
                            viewModel.register()
                        } else {
                            viewModel.login()
                        }

                    },
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.large))
                ) {
                    Text(
                        text =
                            if (viewModel.register.value) stringResource(R.string.register)
                            else stringResource(R.string.login),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.normal))
                        .padding(vertical = dimensionResource(R.dimen.large)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = {
                        viewModel.register.value = !viewModel.register.value
                        viewModel.confirmPassword.value = ""
                    }) {
                        Text(
                            text =
                                if (viewModel.register.value) stringResource(R.string.login)
                                else stringResource(R.string.register),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }

                if (viewModel.errorMessage.value.isNotEmpty() && viewModel.errorMessage.value != "success") {
                    Text(
                        text = viewModel.errorMessage.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(vertical = dimensionResource(R.dimen.large))
                            .padding(horizontal = dimensionResource(R.dimen.normal))
                    )
                }
            }
        }
    }
}
