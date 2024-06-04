package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.movement.MovementWithPlacesDTO
import com.nyakshoot.storageservice.data.dto.movement.NewMovementRequestDTO
import com.nyakshoot.storageservice.data.dto.movement.NewMovementResponseDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IMovementClient
import com.nyakshoot.storageservice.domain.repository.IMovementRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject

class MovementRepositoryImpl @Inject constructor(
    private val iMovementClient: IMovementClient
) : AbstractBaseClient(), IMovementRepository {
    override suspend fun createMovement(newMovementRequestDTO: NewMovementRequestDTO): Resource<NewMovementResponseDTO> {
        return safeApiCall { iMovementClient.createMovement(newMovementRequestDTO) }
    }

    override suspend fun getMovementRequests(): Resource<List<MovementWithPlacesDTO>> {
        return safeApiCall { iMovementClient.getMovementsRequests() }
    }
}