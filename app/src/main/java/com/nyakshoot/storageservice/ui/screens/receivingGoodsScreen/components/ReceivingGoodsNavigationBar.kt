package com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.nyakshoot.storageservice.ui.navigation.Screen
import com.nyakshoot.storageservice.ui.navigation.rememberNavigationState
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
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary,
            content = {
                listItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute(navigationState.navHostController) == item.route,
                        onClick = { navigationState.navigateTo(item.route) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconId),
                                contentDescription = null
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.secondary,
                            indicatorColor = Color.LightGray
                        )
                    )
                }
            }
        )
    }
}