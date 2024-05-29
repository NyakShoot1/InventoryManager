package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.place.PlaceDTO
import com.nyakshoot.storageservice.data.dto.place.PlaceWithPositionsDTO
import com.nyakshoot.storageservice.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPlaceClient {

    @GET("${AppConstants.BASE_URL}place/places/{storage_id}/")
    suspend fun getPlacesByStorageIdWithPositions(
        @Path("storage_id") storageId: Int
    ): Response<List<PlaceWithPositionsDTO>>

    @GET("${AppConstants.BASE_URL}place/places/places_code/")
    suspend fun getPlacesCodes(): Response<List<Char>>

    @GET("${AppConstants.BASE_URL}place/places/places_by_code/")
    suspend fun getPlacesByCode(
        @Query("storage_id") storageId: Int,
        @Query("place_code") placeCode: Char
    ): Response<List<PlaceDTO>>
}