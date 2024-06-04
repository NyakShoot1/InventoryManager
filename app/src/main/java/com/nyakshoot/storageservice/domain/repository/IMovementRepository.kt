package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.movement.MovementWithPlacesDTO
import com.nyakshoot.storageservice.data.dto.movement.NewMovementRequestDTO
import com.nyakshoot.storageservice.data.dto.movement.NewMovementResponseDTO
import com.nyakshoot.storageservice.utils.Resource

interface IMovementRepository {

    suspend fun createMovement(newMovementRequestDTO: NewMovementRequestDTO): Resource<NewMovementResponseDTO>

    suspend fun getMovementRequests(): Resource<List<MovementWithPlacesDTO>>

}