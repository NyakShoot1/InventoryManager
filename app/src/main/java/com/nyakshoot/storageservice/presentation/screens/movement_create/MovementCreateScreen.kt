package com.nyakshoot.storageservice.presentation.screens.movement_create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.presentation.screens.movement_create.components.FilterMovementCreateButton
import com.nyakshoot.storageservice.presentation.screens.movement_create.components.MovementPositionCard
import com.nyakshoot.storageservice.presentation.screens.movement_create.components.StorageCard
import com.nyakshoot.storageservice.utils.Resource

@Composable
fun CreateMovementScreen(
    navController: NavController,
    viewModel: MovementCreateViewModel = hiltViewModel()
) {
    val selectedFilter = remember { mutableStateOf("Внутреннее") }
    val unavailablePlaces = remember { mutableStateOf<PlaceWithPositionsDTO?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getPlaces(1)
        viewModel.getWhereStorages()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.padding(start = 32.dp), text = "Тип перемещения: ")
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FilterMovementCreateButton(
                filterText = "Внутреннее",
                isSelected = selectedFilter.value == "Внутреннее"
            ) {
                selectedFilter.value = "Внутреннее"
            }
            FilterMovementCreateButton(
                filterText = "Внешнее",
                isSelected = selectedFilter.value == "Внешнее"
            ) {
                selectedFilter.value = "Внешнее"
            }
        }

        when {
            viewModel.movementCreateUIState.value.fromPlaces.status == Resource.Status.SUCCESS &&
                    viewModel.movementCreateUIState.value.wherePlaces.status == Resource.Status.SUCCESS &&
                    viewModel.movementCreateUIState.value.whereStorages.status == Resource.Status.SUCCESS -> {
                Column {
                    Text(modifier = Modifier.padding(start = 10.dp), text = "Откуда: ")
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(viewModel.movementCreateUIState.value.fromPlaces.data!!) { place ->
                            MovementPositionCard(
                                place = place,
                                isSelected = viewModel.movementCreateUIState.value.selectedFromPlace.value == place,
                                onSelected = {
                                    if (it) {
                                        viewModel.setSelectedFromPlace(place)
                                        unavailablePlaces.value = place
                                    } else {
                                        viewModel.setSelectedFromPlace(null)
                                        unavailablePlaces.value = null
                                    }
                                }
                            )
                        }
                    }
                    Text(modifier = Modifier.padding(start = 10.dp), text = "Куда: ")
                    if (selectedFilter.value == "Внутреннее") {
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(
                                viewModel.movementCreateUIState.value.wherePlaces.data
                                    ?.filter { it != unavailablePlaces.value }
                                    ?: emptyList()
                            ) { place ->
                                MovementPositionCard(
                                    place = place,
                                    isSelected = viewModel.movementCreateUIState.value.selectedWherePlace.value == place,
                                    onSelected = {
                                        if (it) {
                                            viewModel.setSelectedWherePlace(place)
                                        } else {
                                            viewModel.setSelectedWherePlace(null)
                                        }
                                    }
                                )
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(
                                viewModel.movementCreateUIState.value.whereStorages.data
                                    ?: emptyList()
                            ) { storage ->
                                StorageCard(
                                    storage = storage,
                                    isSelected = viewModel.movementCreateUIState.value.selectedWhereStorage.value == storage,
                                    onSelected = {
                                        if (it) {
                                            viewModel.setSelectedWhereStorage(storage)
                                        } else {
                                            viewModel.setSelectedWhereStorage(null)
                                        }
                                    }
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            viewModel.createNewMovement()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Завершить", color = MaterialTheme.colors.secondary)
                    }
                }
            }

            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
    val context = LocalContext.current
    when (viewModel.doneRequestState.value.status){
        Resource.Status.SUCCESS -> {
            Toast.makeText(context, "Успешно выполнено", Toast.LENGTH_SHORT).show()
            navController.popBackStack(Screen.CreateMovement.route, inclusive = true)
        }
        Resource.Status.ERROR -> {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
        }
        Resource.Status.LOADING -> { }
    }
}