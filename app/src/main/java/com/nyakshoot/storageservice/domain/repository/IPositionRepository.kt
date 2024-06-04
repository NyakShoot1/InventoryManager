package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.position.PositionDTO
import com.nyakshoot.storageservice.data.dto.position.PositionWithItemAndPlaceDTO
import com.nyakshoot.storageservice.data.dto.position.UpdatePlacePositionDTO
import com.nyakshoot.storageservice.utils.Resource

interface IPositionRepository {

    suspend fun getPositionsWithItemAndPlace(): Resource<List<PositionWithItemAndPlaceDTO>>

    suspend fun setPlaceToPosition(place: UpdatePlacePositionDTO): Resource<PositionDTO>
}