package com.nyakshoot.storageservice.ui.screens.receivingGoodsScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.ui.navigation.Screen
import com.nyakshoot.storageservice.ui.navigation.rememberNavigationState
import com.nyakshoot.storageservice.utils.currentRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceivingGoodsTopAppBar() {
    val navigationState = rememberNavigationState()
    val navController = navigationState.navHostController
//    val listItems = listOf(
//        Screen.InputDeliveryData,
//        Screen.InputGoodsData,
//        Screen.DoneReceivingGoods
//    )
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        TopAppBar(
            modifier = Modifier,
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.backspace),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            actions = {
                      if (currentRoute(navController) == Screen.InputGoodsData.route){
                          Icon(
                              modifier = Modifier
                                  .padding(end = 5.dp)
                                  .size(40.dp)
                                  .clickable {},
                              painter = painterResource(id = R.drawable.menu_dots_circle),
                              contentDescription = null,
                              tint = MaterialTheme.colorScheme.secondary
                          )
                      }
            },
            title = {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    textAlign = TextAlign.Start,
                    text = "Приём товара",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}