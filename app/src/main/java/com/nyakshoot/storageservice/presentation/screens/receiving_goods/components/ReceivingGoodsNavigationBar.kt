package com.nyakshoot.storageservice.presentation.screens.receiving_goods.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.utils.currentRoute


@Composable
fun ReceivingGoodsNavigationBar(navController: NavHostController) {
    val listItems = listOf(
        Screen.InputDeliveryData,
        Screen.InputGoodsData,
        Screen.DoneReceivingGoods
    )

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { 0 }),
        exit = slideOutVertically(targetOffsetY = { 0 }),
    ) {
        BottomAppBar(
            contentColor = MaterialTheme.colors.secondary,
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listItems.forEach { item ->
                        Spacer(modifier = Modifier.weight(1f))
                        BottomNavigationItem(
                            selected = currentRoute(navController) == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(Screen.InputDeliveryData.route){
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.iconId),
                                    contentDescription = null
                                )
                            },
                            selectedContentColor = MaterialTheme.colors.secondary,
                            unselectedContentColor = MaterialTheme.colors.secondary,
                            selectedBorderColor = Color(0x1AD9D9D9),
                            borderWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        )
    }
}