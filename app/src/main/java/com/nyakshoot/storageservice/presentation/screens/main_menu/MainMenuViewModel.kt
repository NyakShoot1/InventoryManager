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
): ViewModel() {

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
            ) { Log.d("${R.string.received_delivery}", "Кнопка нажата") },
            MainMenuItemModel(
                label = R.string.completed_orders,
                iconId = R.drawable.delivery_truck
            ) { Log.d("${R.string.completed_orders}", "Кнопка нажата") },
            MainMenuItemModel(
                label = R.string.goods_on_storage,
                iconId = R.drawable.boxes_inside_a_storage
            ) { Log.d("${R.string.goods_on_storage}", "Кнопка нажата") },

            MainMenuItemModel(
                label = R.string.phys_places_on_storage,
                iconId = R.drawable.placeholder_on_map
            ) { Log.d("${R.string.phys_places_on_storage}", "Кнопка нажата") }
        )
    }

    private fun prepareStockMovementItems(navController: NavController): List<MainMenuItemModel> {
        return listOf(
            MainMenuItemModel(
                label = R.string.inside_storage_movement,
                iconId = R.drawable.packages_transportation
            ) { Log.d("${R.string.inside_storage_movement}", "Кнопка нажата")},
            MainMenuItemModel(
                label = R.string.outside_storage_movement,
                iconId = R.drawable.delivery_truck_with_packages
            ) { Log.d("${R.string.outside_storage_movement}", "Кнопка нажата")},
            MainMenuItemModel(
                label = R.string.requests_storage_movement,
                iconId = R.drawable.logistics_delivery_truck
            ) { Log.d("${R.string.requests_storage_movement}", "Кнопка нажата")},
            MainMenuItemModel(
                label = R.string.create_request_storage_movement,
                iconId = R.drawable.logistics_truck
            ) { Log.d("${R.string.create_request_storage_movement}", "Кнопка нажата")}
        )
    }

    private fun prepareTakingItems(navController: NavController): List<MainMenuItemModel> {
        return listOf(
            MainMenuItemModel(
                label = R.string.get_delivery,
                iconId = R.drawable.clipboard_verification
            ) { navController.navigate(Screen.InputDeliveryData.route)})
    }

    private fun prepareReportItems(navController: NavController): List<MainMenuItemModel> {
        return listOf(
            MainMenuItemModel(
                label = R.string.write_downs,
                iconId = R.drawable.recycle
            ) { Log.d("${R.string.write_downs}", "Кнопка нажата")})
    }

    private fun prepareAllItems(navController: NavController): List<List<MainMenuItemModel>> {
        val stockItems = prepareStockItems(navController)
        val stockMovementItems = prepareStockMovementItems(navController)
        val takingItems = prepareTakingItems(navController)
        val reportItems = prepareReportItems(navController)

        return listOf(stockItems, stockMovementItems, takingItems, reportItems)
    }

    object MainMenuTextList {
        val textList = listOf(
            R.string.storage_label,
            R.string.storage_movement_label,
            R.string.get_delivery_label,
            R.string.reports_label
        )
    }
}