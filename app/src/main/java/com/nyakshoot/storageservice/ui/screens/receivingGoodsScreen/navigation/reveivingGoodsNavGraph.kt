package com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nyakshoot.storageservice.ui.navigation.Screen

fun NavGraphBuilder.receivingGoodsNavGraph(
    inputDeliveryDataScreenContent: @Composable () -> Unit,
    inputGoodsDataScreenContent: @Composable () -> Unit,
    doneReceivingGoodsScreenContent: @Composable () -> Unit
){
    navigation(
        startDestination = Screen.InputDeliveryData.route,
        route = Screen.ReceivingGoods.route
    ){
        composable(Screen.InputDeliveryData.route) {
            inputDeliveryDataScreenContent()
        }
        composable(Screen.InputGoodsData.route) {
            inputGoodsDataScreenContent()
        }
        composable(Screen.DoneReceivingGoods.route) {
            doneReceivingGoodsScreenContent()
        }
    }
}