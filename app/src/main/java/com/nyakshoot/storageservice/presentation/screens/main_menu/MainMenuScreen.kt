package com.nyakshoot.storageservice.presentation.screens.main_menu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nyakshoot.storageservice.presentation.screens.main_menu.MainMenuViewModel.MainMenuTextList.textList
import com.nyakshoot.storageservice.presentation.screens.main_menu.components.item.MainMenuItem

@Composable
fun MainMenuScreen(
    navController: NavController,
    viewModel: MainMenuViewModel = hiltViewModel()) {
    viewModel.generateAvailableItems(navController)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        val allItems = viewModel.mainMenuState.availableItems
        allItems?.forEachIndexed { index, element ->
            item {
                Text(
                    text = stringResource(id = textList[index]),
                    fontSize = 24.sp
                )
                LazyRow {
                    element.forEach { itemInElement ->
                        item {
                            MainMenuItem(itemInElement)
                        }
                    }
                }
            }
        }
    }
}

