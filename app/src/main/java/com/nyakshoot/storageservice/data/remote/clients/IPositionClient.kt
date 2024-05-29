package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.position.PositionWithItemAndPlaceDTO
import com.nyakshoot.storageservice.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET

interface IPositionClient {

    @GET("${AppConstants.BASE_URL}position/positions_item_and_place/")
    suspend fun getPositionsWithItemAndPlace(): Response<List<PositionWithItemAndPlaceDTO>>

}