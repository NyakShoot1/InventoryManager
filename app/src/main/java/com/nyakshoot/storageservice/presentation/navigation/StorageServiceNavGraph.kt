package com.nyakshoot.storageservice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.presentation.screens.item_base.ItemBaseScreen
import com.nyakshoot.storageservice.presentation.screens.item_detail.ItemDetailScreen
import com.nyakshoot.storageservice.presentation.screens.login.LoginScreen
import com.nyakshoot.storageservice.presentation.screens.main_menu.MainMenuScreen
import com.nyakshoot.storageservice.presentation.screens.movement_create.CreateMovementScreen
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
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.ItemBase.route) {
            ItemBaseScreen(navController = navController)
        }
        composable(Screen.ItemDetail.route,
            arguments = listOf(
                navArgument("item"){ type = NavType.StringType}
            )
        ) {
            it.arguments?.getString("item")?.let {json ->
                val item = Gson().fromJson(json, ItemDTO::class.java)
                ItemDetailScreen(item)
            }
        }
        composable(Screen.CreateMovement.route){
            CreateMovementScreen(navController = navController)
        }
        receivingGoodsNavGraph(navController = navController)
    }
}
