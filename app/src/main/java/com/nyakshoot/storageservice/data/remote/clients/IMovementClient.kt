package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.movement.MovementWithPlacesDTO
import com.nyakshoot.storageservice.data.dto.movement.NewMovementRequestDTO
import com.nyakshoot.storageservice.data.dto.movement.NewMovementResponseDTO
import com.nyakshoot.storageservice.utils.AppConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IMovementClient {
    @POST("${AppConstants.BASE_URL}movement/create_movement/")
    suspend fun createMovement(
        @Body newMovement: NewMovementRequestDTO,
    ): Response<NewMovementResponseDTO>

    @GET("${AppConstants.BASE_URL}movement/movements_with_places/")
    suspend fun getMovementsRequests(): Response<List<MovementWithPlacesDTO>>
}