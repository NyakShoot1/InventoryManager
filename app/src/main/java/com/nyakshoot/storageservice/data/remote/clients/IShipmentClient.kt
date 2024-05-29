package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.shipment.ShipmentCreateRequestDTO
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import com.nyakshoot.storageservice.utils.AppConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IShipmentClient {
    @POST("${AppConstants.BASE_URL}shipment/create_shipment/")
    suspend fun createShipment(
        @Body newShipmentRequest: ShipmentCreateRequestDTO
    ): Response<ShipmentDTO>

    @GET("${AppConstants.BASE_URL}shipment/shipments/")
    suspend fun getDoneShipments(): Response<List<ShipmentDTO>>

}