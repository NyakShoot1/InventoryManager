package com.nyakshoot.storageservice.presentation.screens.positions

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.positions.components.FilterPositionsButton
import com.nyakshoot.storageservice.presentation.screens.positions.components.PositionCard
import com.nyakshoot.storageservice.utils.Resource

@Composable
fun PositionsScreen(
    viewModel: PositionsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val selectedFilter = remember { mutableStateOf("Все") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getPositionsWithItemAndPlace()
    }

    when (viewModel.positionsUIState.value.positions.status) {
        Resource.Status.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Resource.Status.SUCCESS -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    FilterPositionsButton(
                        filterText = "Все",
                        isSelected = selectedFilter.value == "Все"
                    ) {
                        selectedFilter.value = "Все"
                        viewModel.filterPositions("Все")
                    }
                    FilterPositionsButton(
                        filterText = "На складе",
                        isSelected = selectedFilter.value == "На складе"
                    ) {
                        selectedFilter.value = "На складе"
                        viewModel.filterPositions("На складе")
                    }
                    FilterPositionsButton(
                        filterText = "Новые",
                        isSelected = selectedFilter.value == "Новые"
                    ) {
                        selectedFilter.value = "Новые"
                        viewModel.filterPositions("Новые")
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(viewModel.positionsUIState.value.filteredPositions) { position ->
                        PositionCard(position) {
                            val positionIdJson =
                                Gson().toJson(position.id)
                            navController.navigate(Screen.PositionSetPlace.route.replace("{positionId}", positionIdJson))
                        }
                    }
                }
            }

        }

        Resource.Status.ERROR -> {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}