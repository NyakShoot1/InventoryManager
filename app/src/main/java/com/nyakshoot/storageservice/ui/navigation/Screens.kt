package com.nyakshoot.storageservice.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nyakshoot.storageservice.R

sealed class Screen(val title: String, val iconId: Int, val route: String){
    object Login : Screen("Логин", -1, Destinations.LOGIN_ROUTE)
    object MainMenu : Screen("Главное меню", -1, Destinations.MAIN_MENU_ROUTE)

    object ReceivingGoods : Screen("Принять поставку", -1, Destinations.RECEIVING_GOODS_ROUTE)
    object InputDeliveryData : Screen("Данные", R.drawable.document_text, Destinations.INPUT_DELIVERY_DATA_ROUTE)
    object InputGoodsData : Screen("Товары", R.drawable.cart_plus, Destinations.INPUT_GOODS_DATA_ROUTE)
    object DoneReceivingGoods : Screen("Готово", R.drawable.document_add, Destinations.DONE_RECEIVING_GOODS_ROUTE)


}

object Destinations {
    const val LOGIN_ROUTE = "login"
    const val MAIN_MENU_ROUTE = "main_menu"

    const val RECEIVING_GOODS_ROUTE = "receiving_goods"
    const val INPUT_DELIVERY_DATA_ROUTE = "input_delivery_data"
    const val INPUT_GOODS_DATA_ROUTE = "input_goods_data"
    const val DONE_RECEIVING_GOODS_ROUTE = "done_receiving_goods"




    const val ORDER_ROUTE = "order"
    const val SHIPMENT_ROUTE = "shipment"
    const val SETTINGS_ROUTE = "settings"
    const val STATISTIC_ROUTE = "statistic"
    const val STORAGE_ROUTE = "storage"
    const val MAIN_ROUTE = "main"
}