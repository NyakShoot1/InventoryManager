package com.nyakshoot.storageservice.presentation.screens.movement_requests

import com.nyakshoot.storageservice.data.dto.movement.MovementWithPlacesDTO
import com.nyakshoot.storageservice.utils.Resource

data class MovementRequestsUIState(
    val movements: Resource<List<MovementWithPlacesDTO>> = Resource.loading(),
    val expandedMovementId: Int? = null
)
