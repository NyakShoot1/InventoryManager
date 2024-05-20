package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.position.PositionDTO
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import com.nyakshoot.storageservice.utils.AppConstants
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface IShipmentClient {
    @Multipart
    @POST("${AppConstants.BASE_URL}shipment/create_shipment/")
    suspend fun createShipment(
        @Query("delivery_man") deliveryMan: String,
        @Query("supplier_name") supplierName: String,
        @Query("document_number") documentNumber: String,
        @Query("storage_man") storageMan: String,
        @Part("new_positions") newPositions: List<PositionDTO>,
        @Part photos: List<MultipartBody.Part>,
    ): Response<ShipmentDTO>

}