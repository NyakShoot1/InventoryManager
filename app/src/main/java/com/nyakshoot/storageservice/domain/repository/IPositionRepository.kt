package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.position.PositionWithItemAndPlaceDTO
import com.nyakshoot.storageservice.utils.Resource

interface IPositionRepository {

    suspend fun getPositionsWithItemAndPlace(): Resource<List<PositionWithItemAndPlaceDTO>>

}