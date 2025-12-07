package com.raaveinm.hybriddrive_android.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.raaveinm.hybriddrive_android.ui.navigation.NavData
import com.raaveinm.hybriddrive_android.ui.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeScreenViewModel = viewModel()
    ) {
    val isLogged by viewModel.isLogged.collectAsState()

    when(isLogged) {
        true -> {
            // permission request
        }
        false -> {
            navController.navigate(NavData.Auth.route) {
                popUpTo(NavData.Files.route) { inclusive = true }
            }
        }
        else -> {

        }
    }
}
