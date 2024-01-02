package com.nyakshoot.storageservice.presentation.screens.main_menu

import com.nyakshoot.storageservice.presentation.screens.main_menu.components.item.MainMenuItemModel

data class MainMenuState(
    val availableItems: List<List<MainMenuItemModel>>? = listOf()
)
