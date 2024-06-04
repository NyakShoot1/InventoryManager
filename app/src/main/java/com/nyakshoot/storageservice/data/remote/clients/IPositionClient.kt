package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.position.PositionDTO
import com.nyakshoot.storageservice.data.dto.position.PositionWithItemAndPlaceDTO
import com.nyakshoot.storageservice.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface IPositionClient {

    @GET("${AppConstants.BASE_URL}position/positions_item_and_place/")
    suspend fun getPositionsWithItemAndPlace(): Response<List<PositionWithItemAndPlaceDTO>>

    @PUT("${AppConstants.BASE_URL}position/add_place/")
    suspend fun setPlaceToPosition(
        @Query("position_id") positionId: Int,
        @Query("storage_id") storageId: Int,
        @Query("place_code") placeCode: Char,
        @Query("place_number") placeNumber: Int
    ): Response<PositionDTO>
}