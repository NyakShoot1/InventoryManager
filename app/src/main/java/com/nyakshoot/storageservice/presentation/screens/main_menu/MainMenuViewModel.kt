package com.nyakshoot.storageservice.presentation.screens.main_menu

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.main_menu.components.item.MainMenuItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
) : ViewModel() {

    // ui стейт
    var mainMenuState by mutableStateOf(MainMenuState())
        private set

    fun generateAvailableItems(navController: NavController) {
        mainMenuState = mainMenuState.copy(availableItems = prepareAllItems(navController))
    }

    private fun prepareStockItems(navController: NavController): List<MainMenuItemModel> {
        return listOf(
            MainMenuItemModel(
                label = R.string.received_delivery,
                iconId = R.drawable.delivered_box_verification
            ) {
                navController.navigate(Screen.DoneShipments.route)
                Log.d("${R.string.received_delivery}", "Кнопка нажата")
            },

            MainMenuItemModel(
                label = R.string.completed_orders,
                iconId = R.drawable.delivery_truck
            ) {
                Log.d("${R.string.completed_orders}", "Кнопка нажата")
            },

            MainMenuItemModel(
                label = R.string.goods_on_storage,
                iconId = R.drawable.boxes_inside_a_storage
            ) {
                navController.navigate(Screen.Positions.route)
                Log.d("${R.string.goods_on_storage}", "Кнопка нажата")
            },

            MainMenuItemModel(
                label = R.string.phys_places_on_storage,
                iconId = R.drawable.placeholder_on_map,
            ) {
                navController.navigate(Screen.Places.route)
                Log.d("${R.string.phys_places_on_storage}", "Кнопка нажата")
            },

            MainMenuItemModel(
                label = R.string.goods_base,
                iconId = R.drawable.boxes_inside_a_storage,
            ) {
                navController.navigate(Screen.ItemBase.route)
                Log.d("${R.string.goods_base}", "Кнопка нажата")
            },

            )
    }

    private fun prepareStockMovementItems(navController: NavController): List<MainMenuItemModel> {
        return listOf(
            MainMenuItemModel(
                label = R.string.inside_storage_movement,
                iconId = R.drawable.packages_transportation,
            ) { Log.d("${R.string.inside_storage_movement}", "Кнопка нажата") },
            MainMenuItemModel(
                label = R.string.outside_storage_movement,
                iconId = R.drawable.delivery_truck_with_packages
            ) { Log.d("${R.string.outside_storage_movement}", "Кнопка нажата") },
            MainMenuItemModel(
                label = R.string.requests_storage_movement,
                iconId = R.drawable.logistics_delivery_truck
            ) {
                navController.navigate(Screen.MovementRequests.route)
                Log.d("${R.string.requests_storage_movement}", "Кнопка нажата")
              },
        )
    }

    private fun prepareStorageOperationsItems(navController: NavController): List<MainMenuItemModel> {
        return listOf(
            MainMenuItemModel(
                label = R.string.get_delivery,
                iconId = R.drawable.clipboard_verification
            ) {
                navController.navigate(Screen.InputDeliveryData.route)
            },
            MainMenuItemModel(
                label = R.string.create_request_storage_movement,
                iconId = R.drawable.logistics_truck
            ) {
                navController.navigate(Screen.CreateMovement.route)
                Log.d("${R.string.create_request_storage_movement}", "Кнопка нажата")
            },
            MainMenuItemModel(
                label = R.string.delivery,
                iconId = R.drawable.delivery_svgrepo_com
            ) {

                Log.d("${R.string.create_request_storage_movement}", "Кнопка нажата")
            },
            MainMenuItemModel(
                label = R.string.write_downs,
                iconId = R.drawable.recycle
            ) { Log.d("${R.string.write_downs}", "Кнопка нажата") },
        )
    }

    private fun prepareStatisticItems(navController: NavController): List<MainMenuItemModel> {
        return listOf(
            MainMenuItemModel(
                label = R.string.sales_statistics,
                iconId = R.drawable.round_query_stats_24
            ) { Log.d("${R.string.write_downs}", "Кнопка нажата") }
        )
    }

    private fun prepareAllItems(navController: NavController): List<List<MainMenuItemModel>> {
        val stockItems = prepareStockItems(navController)
        val stockMovementItems = prepareStockMovementItems(navController)
        val storageOperationsItems = prepareStorageOperationsItems(navController)
        val statisticItems = prepareStatisticItems(navController)

        return listOf(stockItems, stockMovementItems, storageOperationsItems, statisticItems)
    }

    object MainMenuTextList {
        val textList = listOf(
            R.string.storage_label,
            R.string.storage_movement_label,
            R.string.get_delivery_label,
            R.string.statistic_label
        )
    }
}