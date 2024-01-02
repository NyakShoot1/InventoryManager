package com.nyakshoot.storageservice.presentation.screens.receiving_goods.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nyakshoot.storageservice.presentation.navigation.Screen

fun NavGraphBuilder. receivingGoodsNavGraph(){
    navigation(
        startDestination = Screen.InputDeliveryData.route,
        route = Screen.ReceivingGoods.route
    ){
        composable(Screen.InputDeliveryData.route) {

        }
        composable(Screen.InputGoodsData.route) {

        }
        composable(Screen.DoneReceivingGoods.route) {

        }
    }
}