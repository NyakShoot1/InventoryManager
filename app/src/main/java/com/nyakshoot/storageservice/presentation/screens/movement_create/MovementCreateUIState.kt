package com.nyakshoot.storageservice.presentation.screens.movement_create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.nyakshoot.storageservice.data.dto.position.PositionDTO
import com.nyakshoot.storageservice.data.dto.storage.StorageDTO
import com.nyakshoot.storageservice.utils.Resource

data class MovementCreateUIState (
    val requestType: MutableState<Boolean> = mutableStateOf(false),
    val visibleMenuState: MutableState<Boolean> = mutableStateOf(false),
    val fromPositions: Resource<List<PositionDTO>> = Resource.loading(),
    val wherePositions: Resource<List<PositionDTO>> = Resource.loading(),
    val whereStorages: Resource<List<StorageDTO>> = Resource.loading(),
)