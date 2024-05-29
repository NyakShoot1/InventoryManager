package com.nyakshoot.storageservice.data.dto.place

import com.nyakshoot.storageservice.data.dto.position.PositionWithItemDTO

data class PlaceWithPositionsDTO (
    val place: PlaceDTO,
    val positions: List<PositionWithItemDTO>
)