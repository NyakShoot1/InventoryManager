package com.nyakshoot.storageservice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nyakshoot.storageservice.presentation.screens.login.LoginScreen
import com.nyakshoot.storageservice.presentation.screens.main_menu.MainMenuScreen
import com.nyakshoot.storageservice.presentation.screens.profile.ProfileScreen
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.navigation.receivingGoodsNavGraph

@Composable
fun StorageServiceNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.MainMenu.route) {
            MainMenuScreen(navController = navController)
        }
        composable(Screen.InputDeliveryData.route) {
            receivingGoodsNavGraph()
        }
        composable(Screen.Profile.route){
            ProfileScreen(navController = navController)
        }
    }
}
