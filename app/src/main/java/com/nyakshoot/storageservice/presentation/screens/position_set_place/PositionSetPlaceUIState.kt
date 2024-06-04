package com.nyakshoot.storageservice.presentation.screens.position_set_place

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.utils.Resource

data class PositionSetPlaceUIState(
    val places: Resource<List<PlaceWithPositionsDTO>> = Resource.loading(),
    val selectedPlace: MutableState<PlaceWithPositionsDTO?> = mutableStateOf(null),
    var selectedPosition: Int = 0
)
