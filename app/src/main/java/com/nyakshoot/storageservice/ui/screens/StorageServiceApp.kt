package com.nyakshoot.storageservice.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.ui.navigation.Screen
import com.nyakshoot.storageservice.ui.navigation.StorageServiceNavGraph
import com.nyakshoot.storageservice.ui.navigation.rememberNavigationState
import com.nyakshoot.storageservice.ui.screens.loginScreen.LoginScreen
import com.nyakshoot.storageservice.ui.screens.mainMenuScreen.MainMenuScreen
import com.nyakshoot.storageservice.ui.screens.mainMenuScreen.MainMenuTopAppBar
import com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.DoneReceivingGoodsScreen
import com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.InputDeliveryDataScreen
import com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.InputGoodsDataScreen
import com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.components.ReceivingGoodsNavigationBar
import com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.components.ReceivingGoodsTopAppBar
import com.nyakshoot.storageservice.utils.currentRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageServiceApp() {
    val navigationState = rememberNavigationState()
    val navController = navigationState.navHostController

    Scaffold(
        topBar = {
            when (currentRoute(navController)) {
                Screen.MainMenu.route -> MainMenuTopAppBar()
                Screen.InputDeliveryData.route,
                Screen.InputGoodsData.route,
                Screen.DoneReceivingGoods.route -> ReceivingGoodsTopAppBar()
            }

        },
        bottomBar = {
            when (currentRoute(navController)) {
                Screen.InputDeliveryData.route,
                Screen.InputGoodsData.route,
                Screen.DoneReceivingGoods.route -> ReceivingGoodsNavigationBar()
            }
        },
        floatingActionButton = {
            when (currentRoute(navController)) {
                Screen.InputGoodsData.route ->
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(80.dp),
                            painter = painterResource(id = R.drawable.scanner),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            StorageServiceNavGraph(
                navController,
                loginScreenContent = {
                    LoginScreen(){
                        navigationState.navigateFromLoginScreenToMainMenuScreen()
                    }
                },
                mainMenuScreenContent = {
                    MainMenuScreen()
                },
                inputDeliveryDataScreenContent = {
                    InputDeliveryDataScreen()
                },
                inputGoodsDataScreenContent = {
                    InputGoodsDataScreen()
                },
                doneReceivingGoodsScreenContent = {
                    DoneReceivingGoodsScreen()
                }
            )
        }
    }
}