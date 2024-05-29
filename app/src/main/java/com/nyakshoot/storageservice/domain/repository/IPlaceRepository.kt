package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.place.PlaceDTO
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.utils.Resource

interface IPlaceRepository {

    suspend fun getPlacesByStorageId(storageId: Int): Resource<List<PlaceWithPositionsDTO>>
    suspend fun getPlacesCodes(): Resource<List<Char>>
    suspend fun getPlacesByCode(storageId: Int, placeCode: Char): Resource<List<PlaceDTO>>

}