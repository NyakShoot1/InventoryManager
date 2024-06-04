package com.nyakshoot.storageservice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.DoneShipmentDetailScreen
import com.nyakshoot.storageservice.presentation.screens.done_shipments.DoneShipmentsScreen
import com.nyakshoot.storageservice.presentation.screens.item_detail.ItemDetailScreen
import com.nyakshoot.storageservice.presentation.screens.items.ItemBaseScreen
import com.nyakshoot.storageservice.presentation.screens.login.LoginScreen
import com.nyakshoot.storageservice.presentation.screens.main_menu.MainMenuScreen
import com.nyakshoot.storageservice.presentation.screens.movement_create.CreateMovementScreen
import com.nyakshoot.storageservice.presentation.screens.movement_requests.MovementRequestsScreen
import com.nyakshoot.storageservice.presentation.screens.places.PlacesScreen
import com.nyakshoot.storageservice.presentation.screens.position_set_place.PositionSetPlaceScreen
import com.nyakshoot.storageservice.presentation.screens.positions.PositionsScreen
import com.nyakshoot.storageservice.presentation.screens.profile.ProfileScreen
import com.nyakshoot.storageservice.presentation.screens.receiving_goods.navigation.receivingGoodsNavGraph

@Composable
fun StorageServiceNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.MainMenu.route) {
            MainMenuScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.DoneShipments.route) {
            DoneShipmentsScreen(navController = navController)
        }
        composable(Screen.DoneShipmentDetail.route,
            arguments = listOf(
                navArgument("shipment") { type = NavType.StringType }
            )
        ) {
            it.arguments?.getString("shipment")?.let { json ->
                val shipment = Gson().fromJson(json, ShipmentDTO::class.java)
                DoneShipmentDetailScreen(navController = navController, shipment)
            }
        }
        composable(Screen.Positions.route) {
            PositionsScreen(navController = navController)
        }
        composable(Screen.PositionSetPlace.route,
            arguments = listOf(
                navArgument("positionId") { type = NavType.IntType }
            )
        ) {
            it.arguments?.getInt("positionId")?.let { positionId ->
                PositionSetPlaceScreen(navController = navController, positionId = positionId)
            }
        }
        composable(Screen.MovementRequests.route){
            MovementRequestsScreen(navController = navController)
        }
        composable(Screen.Places.route) {
            PlacesScreen()
        }
        composable(Screen.ItemBase.route) {
            ItemBaseScreen(navController = navController)
        }
        composable(Screen.ItemDetail.route,
            arguments = listOf(
                navArgument("item") { type = NavType.StringType }
            )
        ) {
            it.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, ItemDTO::class.java)
                ItemDetailScreen(item)
            }
        }
        composable(Screen.CreateMovement.route) {
            CreateMovementScreen(navController = navController)
        }
        receivingGoodsNavGraph(navController = navController)
    }
}
