package com.nyakshoot.storageservice.presentation.screens.receiving_goods.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.navigation.rememberNavigationState
import com.nyakshoot.storageservice.utils.currentRoute


@Composable
fun ReceivingGoodsNavigationBar() {
    val navigationState = rememberNavigationState()

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
                listItems.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute(navigationState.navHostController) == item.route,
                        onClick = { navigationState.navigateTo(item.route) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconId),
                                contentDescription = null
                            )
                        },
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = MaterialTheme.colors.secondary,
                        )
                }
            }
        )
    }
}