package com.raaveinm.hybriddrive_android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raaveinm.hybriddrive_android.ui.fragments.TopBar
import com.raaveinm.hybriddrive_android.ui.navigation.NavData
import com.raaveinm.hybriddrive_android.ui.screens.AuthScreen
import com.raaveinm.hybriddrive_android.ui.screens.HomeScreen
import com.raaveinm.hybriddrive_android.ui.screens.ViewFileScreen

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    viewModel: MainAppViewModel = viewModel(),
) {
    val navController: NavHostController = rememberNavController()

    Scaffold (
        modifier = modifier,
        topBar = { TopBar() },
        bottomBar = {}
    ) { it ->
        NavHost(
            navController = navController,
            startDestination =  NavData.Files.name,
            modifier = Modifier.padding(it)
        ) {
            composable(NavData.Files.name) {
                HomeScreen(navController = navController)
            }
            composable(NavData.Upload.name) {  }
            composable(NavData.Auth.name) {
                AuthScreen(navController = navController)
            }
            composable(NavData.View.name) {
                ViewFileScreen(modifier = modifier, navController = navController)
            }
        }
    }
}
