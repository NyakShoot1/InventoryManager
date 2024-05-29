package com.nyakshoot.storageservice.presentation.screens.positions

import com.nyakshoot.storageservice.data.dto.position.PositionWithItemAndPlaceDTO
import com.nyakshoot.storageservice.utils.Resource

data class PositionsUIState(
    val positions: Resource<MutableList<PositionWithItemAndPlaceDTO>> = Resource.loading(),
    val filteredPositions: MutableList<PositionWithItemAndPlaceDTO> = mutableListOf()
)
