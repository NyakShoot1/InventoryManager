package com.nyakshoot.storageservice.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nyakshoot.storageservice.ui.screens.mainMenuScreen.MainMenuScreen
import com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.navigation.receivingGoodsNavGraph

@Composable
fun StorageServiceNavGraph(
    navController: NavHostController,
    loginScreenContent: @Composable () -> Unit,

    mainMenuScreenContent: @Composable () -> Unit,

    inputDeliveryDataScreenContent: @Composable () -> Unit,
    inputGoodsDataScreenContent: @Composable () -> Unit,
    doneReceivingGoodsScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            loginScreenContent()
        }
        composable(Screen.MainMenu.route) {
            mainMenuScreenContent()
        }
        receivingGoodsNavGraph(
            inputDeliveryDataScreenContent,
            inputGoodsDataScreenContent,
            doneReceivingGoodsScreenContent
        )
    }
}