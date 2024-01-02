package com.nyakshoot.storageservice.presentation.screens.main_menu.components.item

data class MainMenuItemModel (
    val label: Int,
    val iconId: Int,
    val onClickStartReceivingGoods: () -> Unit
)


