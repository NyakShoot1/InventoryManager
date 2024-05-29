package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.position.PositionWithItemAndPlaceDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IPositionClient
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PositionRepositoryImpl@Inject constructor(
    private val iPositionClient: IPositionClient
): AbstractBaseClient(), IPositionRepository {
    override suspend fun getPositionsWithItemAndPlace(): Resource<List<PositionWithItemAndPlaceDTO>> {
        return safeApiCall { iPositionClient.getPositionsWithItemAndPlace() }
    }
}