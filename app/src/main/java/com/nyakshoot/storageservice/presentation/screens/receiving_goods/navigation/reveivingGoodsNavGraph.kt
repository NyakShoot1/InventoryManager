package com.nyakshoot.storageservice.presentation.screens.receiving_goods.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.DoneReceivingGoodsScreen
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.InputDeliveryDataScreen
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.InputGoodsDataScreen

fun NavGraphBuilder.receivingGoodsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.InputDeliveryData.route,
        route = Screen.ReceivingGoods.route
    ){
        composable(Screen.InputDeliveryData.route) {
            InputDeliveryDataScreen(navController = navController)
        }
        composable(Screen.InputGoodsData.route) {
            InputGoodsDataScreen(navController = navController)
        }
        composable(Screen.DoneReceivingGoods.route) {
            DoneReceivingGoodsScreen(navController = navController)
        }
    }
}