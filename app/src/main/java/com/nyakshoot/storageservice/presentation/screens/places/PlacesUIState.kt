package com.nyakshoot.storageservice.presentation.screens.places

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.nyakshoot.storageservice.data.dto.place.PlaceDTO
import com.nyakshoot.storageservice.utils.Resource

data class PlacesUIState(
    val filteredState: MutableState<String> = mutableStateOf(""),
    val codePlaces: Resource<List<Char>> = Resource.loading(),
    val places: Resource<MutableList<PlaceDTO>> = Resource.loading(),
    val filteredPlaces: MutableList<PlaceDTO> = mutableListOf()
)

