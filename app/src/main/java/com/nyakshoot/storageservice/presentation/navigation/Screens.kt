package com.nyakshoot.storageservice.presentation.navigation

import com.nyakshoot.storageservice.R

sealed class Screen(val title: String, val iconId: Int, val route: String){
    object Login: Screen("Логин", -1, Destinations.LOGIN_ROUTE)
    object MainMenu: Screen("Главное меню", -1, Destinations.MAIN_MENU_ROUTE)

    object Profile: Screen("Профиль", -1, Destinations.PROFILE_ROUTE)

    object ReceivingGoods: Screen("Принять поставку", -1, Destinations.RECEIVING_GOODS_ROUTE)
    object InputDeliveryData: Screen("Данные", R.drawable.document_text,
        Destinations.INPUT_DELIVERY_DATA_ROUTE
    )
    object InputGoodsData: Screen("Товары", R.drawable.cart_plus,
        Destinations.INPUT_GOODS_DATA_ROUTE
    )
    object DoneReceivingGoods: Screen("Готово", R.drawable.document_add,
        Destinations.DONE_RECEIVING_GOODS_ROUTE
    )
    object ItemBase: Screen("База товаров", -1, Destinations.ITEM_BASE_ROUTE)
    object ItemDetail: Screen("Товар...", -1, Destinations.ITEM_DETAIL_ROUTE)

    object CreateMovement: Screen("Создать запрос на перемещение", -1, Destinations.CREATE_MOVEMENT_ROUTE)

}

object Destinations {
    const val LOGIN_ROUTE = "login_screen"
    const val MAIN_MENU_ROUTE = "main_menu_screen"
    const val PROFILE_ROUTE = "profile_screen"

    const val RECEIVING_GOODS_ROUTE = "receiving_goods_screen"
    const val INPUT_DELIVERY_DATA_ROUTE = "input_delivery_data_screen"
    const val INPUT_GOODS_DATA_ROUTE = "input_goods_data_screen"
    const val DONE_RECEIVING_GOODS_ROUTE = "done_receiving_goods_screen"
    const val ITEM_BASE_ROUTE = "item_base"
    const val ITEM_DETAIL_ROUTE = "item_detail/{item}"

    const val CREATE_MOVEMENT_ROUTE = "create_movement_screen"

    const val ORDER_ROUTE = "order"
    const val SHIPMENT_ROUTE = "shipment"
    const val SETTINGS_ROUTE = "settings"
    const val STATISTIC_ROUTE = "statistic"
    const val STORAGE_ROUTE = "storage"

    const val MAIN_ROUTE = "main"
}