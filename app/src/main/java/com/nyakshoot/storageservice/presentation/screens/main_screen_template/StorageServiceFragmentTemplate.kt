package com.nyakshoot.storageservice.presentation.screens.main_screen_template

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.navigation.StorageServiceNavGraph
import com.nyakshoot.storageservice.presentation.screens.item_base.components.ItemBaseTopAppBar
import com.nyakshoot.storageservice.presentation.screens.main_menu.components.MainMenuTopAppBar
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.components.ReceivingGoodsNavigationBar
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.components.ReceivingGoodsTopAppBar
import com.nyakshoot.storageservice.utils.currentRoute
import kotlinx.coroutines.launch

@Composable
fun StorageServiceFragmentTemplate(
    navController: NavHostController,
    viewModel: StorageServiceViewModel = hiltViewModel()
) {
    val scaffoldState: ScaffoldState =
        rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            when (currentRoute(navController)) {
                Screen.MainMenu.route -> MainMenuTopAppBar(scaffoldState)
                Screen.InputDeliveryData.route,
                Screen.InputGoodsData.route,
                Screen.DoneReceivingGoods.route -> ReceivingGoodsTopAppBar(navController)

                Screen.Profile.route -> ReceivingGoodsTopAppBar(navController)
                Screen.ItemBase.route -> ItemBaseTopAppBar()
            }

        },
        drawerContent = {
            val scope = rememberCoroutineScope()
            Text("Профиль", fontSize = 28.sp, modifier = Modifier.clickable {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navController.navigate(Screen.Profile.route)
            })
            Text("Настройки", fontSize = 28.sp)
        },
        drawerGesturesEnabled = currentRoute(navController) == Screen.MainMenu.route,
        bottomBar = {
            when (currentRoute(navController)) {
                Screen.InputDeliveryData.route,
                Screen.InputGoodsData.route,
                Screen.DoneReceivingGoods.route -> ReceivingGoodsNavigationBar(navController)
            }
        },
        floatingActionButton = {
            when (currentRoute(navController)) {
                Screen.InputGoodsData.route ->
                    IconButton(
                        onClick = {
                            //TODO
                        },
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(80.dp),
                            painter = painterResource(id = R.drawable.scanner),
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                    }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            //Navigation()
            StorageServiceNavGraph(navController)
        }
    }
}
