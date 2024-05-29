package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.place.PlaceDTO
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IPlaceClient
import com.nyakshoot.storageservice.domain.repository.IPlaceRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRepositoryImpl @Inject constructor(
    private val iPlaceClient: IPlaceClient
) : AbstractBaseClient(), IPlaceRepository {
    override suspend fun getPlacesByStorageId(storageId: Int): Resource<List<PlaceWithPositionsDTO>> {
        return safeApiCall { iPlaceClient.getPlacesByStorageIdWithPositions(storageId) }
    }

    override suspend fun getPlacesCodes(): Resource<List<Char>> {
        return safeApiCall { iPlaceClient.getPlacesCodes() }
    }

    override suspend fun getPlacesByCode(storageId: Int, placeCode: Char): Resource<List<PlaceDTO>> {
        return safeApiCall { iPlaceClient.getPlacesByCode(storageId, placeCode) }
    }

}