package com.nyakshoot.storageservice.presentation.screens.movement_create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.data.dto.storage.StorageDTO
import com.nyakshoot.storageservice.utils.Resource

data class MovementCreateUIState (
    val requestType: MutableState<Boolean> = mutableStateOf(false),
    val visibleMenuState: MutableState<Boolean> = mutableStateOf(false),
    val fromPlaces: Resource<List<PlaceWithPositionsDTO>> = Resource.loading(),
    val wherePlaces: Resource<List<PlaceWithPositionsDTO>> = Resource.loading(),
    val whereStorages: Resource<List<StorageDTO>> = Resource.loading(),
    val selectedFromPlace: MutableState<PlaceWithPositionsDTO?> = mutableStateOf(null),
    val selectedWherePlace: MutableState<PlaceWithPositionsDTO?> = mutableStateOf(null),
    val selectedWhereStorage: MutableState<StorageDTO?> = mutableStateOf(null)
)