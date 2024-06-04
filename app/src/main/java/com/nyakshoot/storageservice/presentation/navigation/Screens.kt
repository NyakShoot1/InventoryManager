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

    object DoneShipments: Screen("Поставки", -1, Destinations.DONE_SHIPMENTS_ROUTE)
    object DoneShipmentDetail: Screen("Детали поставки", -1, Destinations.DONE_SHIPMENT_DETAIL_ROUTE)
    object Positions: Screen("Позиции на складе", -1, Destinations.POSITIONS_ROUTE)
    object PositionSetPlace: Screen("Добавить место", -1, Destinations.POSITION_SET_PLACE_ROUTE)

    object Places: Screen("Места на складе", -1, Destinations.PLACES_ROUTE)
    object ItemBase: Screen("База товаров", -1, Destinations.ITEM_BASE_ROUTE)
    object ItemDetail: Screen("Детали товара", -1, Destinations.ITEM_DETAIL_ROUTE)

    object MovementRequests: Screen("Активные запросы", -1, Destinations.MOVEMENT_REQUEST_ROUTE)

    object CreateMovement: Screen("Новый запрос", -1, Destinations.CREATE_MOVEMENT_ROUTE)

}

object Destinations {
    const val LOGIN_ROUTE = "login_screen"
    const val MAIN_MENU_ROUTE = "main_menu_screen"
    const val PROFILE_ROUTE = "profile_screen"

    const val DONE_SHIPMENTS_ROUTE = "done_shipments_screen"
    const val DONE_SHIPMENT_DETAIL_ROUTE = "done_shipment_detail_screen/{shipment}"
    const val POSITIONS_ROUTE = "positions_screen"
    const val POSITION_SET_PLACE_ROUTE = "position_set_place_screen/{positionId}"
    const val PLACES_ROUTE = "places_screen"
    const val RECEIVING_GOODS_ROUTE = "receiving_goods_screen"
    const val INPUT_DELIVERY_DATA_ROUTE = "input_delivery_data_screen"
    const val INPUT_GOODS_DATA_ROUTE = "input_goods_data_screen"
    const val DONE_RECEIVING_GOODS_ROUTE = "done_receiving_goods_screen"
    const val ITEM_BASE_ROUTE = "item_base"
    const val ITEM_DETAIL_ROUTE = "item_detail/{item}"

    const val MOVEMENT_REQUEST_ROUTE = "movement_requests_screen"

    const val CREATE_MOVEMENT_ROUTE = "create_movement_screen"

    const val ORDER_ROUTE = "order"
    const val SHIPMENT_ROUTE = "shipment"
    const val SETTINGS_ROUTE = "settings"
    const val STATISTIC_ROUTE = "statistic"
    const val STORAGE_ROUTE = "storage"

    const val MAIN_ROUTE = "main"
}