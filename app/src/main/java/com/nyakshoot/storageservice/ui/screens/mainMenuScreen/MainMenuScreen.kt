package com.nyakshoot.storageservice.ui.screens.mainMenuScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.ui.navigation.Destinations
import com.nyakshoot.storageservice.ui.navigation.NavigationState
import com.nyakshoot.storageservice.ui.navigation.Screen
import com.nyakshoot.storageservice.ui.navigation.rememberNavigationState

@Composable
fun MainMenuScreen() {
    val navigationState = rememberNavigationState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.storage_label),
                fontSize = 32.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(ScrollState(0))
            ) {
                MainMenuItem(
                    label = R.string.received_delivery,
                    iconId = R.drawable.delivered_box_verification
                ) {

                }
                MainMenuItem(
                    label = R.string.completed_orders,
                    iconId = R.drawable.delivery_truck
                ) {

                }
                MainMenuItem(
                    label = R.string.goods_on_storage,
                    iconId = R.drawable.boxes_inside_a_storage
                ) {

                }
                MainMenuItem(
                    label = R.string.phys_places_on_storage,
                    iconId = R.drawable.placeholder_on_map
                ) {

                }
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.storage_movement_label),
                fontSize = 26.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(ScrollState(0))
            ) {
                MainMenuItem(
                    label = R.string.inside_storage_movement,
                    iconId = R.drawable.packages_transportation
                ) {

                }
                MainMenuItem(
                    label = R.string.outside_storage_movement,
                    iconId = R.drawable.delivery_truck_with_packages
                ) {

                }
                MainMenuItem(
                    label = R.string.requests_storage_movement,
                    iconId = R.drawable.logistics_delivery_truck
                ) {

                }
                MainMenuItem(
                    label = R.string.create_request_storage_movement,
                    iconId = R.drawable.logistics_truck
                ) {

                }
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.get_delivery_label),
                fontSize = 32.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(ScrollState(0))
            ) {
                MainMenuItem(
                    label = R.string.get_delivery,
                    iconId = R.drawable.clipboard_verification
                ) {
                    navigationState.navigateToReceivingGoodsScreen()
                }
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.reports_label),
                fontSize = 32.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(ScrollState(0))
            ) {
                MainMenuItem(
                    label = R.string.write_downs,
                    iconId = R.drawable.recycle
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuTopAppBar() {
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
                        .clickable { }
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.hamburger_menu),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            title = {
                Text(
                    textAlign = TextAlign.Center,
                    text = "",
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

@Composable
fun MainMenuItem(label: Int, iconId: Int, onClickStartReceivingGoods: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClickStartReceivingGoods() }
            .padding(8.dp)
            .height(130.dp)
            .width(130.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(70.dp),
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                modifier = Modifier.padding(start = 2.dp, end = 2.dp), //НАДО?
                text = stringResource(id = label),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}