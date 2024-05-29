package com.nyakshoot.storageservice.presentation.screens.places

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyakshoot.storageservice.presentation.screens.places.components.FilterPlacesButton
import com.nyakshoot.storageservice.presentation.screens.places.components.LetterButton
import com.nyakshoot.storageservice.presentation.screens.places.components.PlaceItem
import com.nyakshoot.storageservice.utils.Resource

@Composable
fun PlacesScreen(
    viewModel: PlacesViewModel = hiltViewModel()
) {
    val selectedLetter = remember { mutableStateOf('A') }
    val selectedFilter = remember { mutableStateOf("Все") }
    val firstLoadedState = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getPlacesCodes()
    }

    when (viewModel.placesUIState.value.codePlaces.status) {
        Resource.Status.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        Resource.Status.SUCCESS -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(0.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "Ряд: "
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp)
                    ) {
                        items(viewModel.placesUIState.value.codePlaces.data!!) { codePlace ->
                            LetterButton(
                                letter = codePlace,
                                isSelected = codePlace == selectedLetter.value
                            ) {
                                selectedLetter.value = codePlace
                                viewModel.getPlacesByCode(1, selectedLetter.value)
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    FilterPlacesButton(
                        filterText = "Все",
                        isSelected = selectedFilter.value == "Все"
                    ) {
                        selectedFilter.value = "Все"
                        viewModel.filterPlaces("Все")
                    }
                    FilterPlacesButton(
                        filterText = "Занятые",
                        isSelected = selectedFilter.value == "Занятые"
                    ) {
                        selectedFilter.value = "Занятые"
                        viewModel.filterPlaces("Занятые")
                    }
                    FilterPlacesButton(
                        filterText = "Пустые",
                        isSelected = selectedFilter.value == "Пустые"
                    ) {
                        selectedFilter.value = "Пустые"
                        viewModel.filterPlaces("Пустые")
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    items(viewModel.placesUIState.value.filteredPlaces) { place ->
                        PlaceItem(place = place){
                            //todo place detail
                        }
                    }
                }
            }
        }
        Resource.Status.ERROR -> {
            // Обработка ошибки
        }
    }
}