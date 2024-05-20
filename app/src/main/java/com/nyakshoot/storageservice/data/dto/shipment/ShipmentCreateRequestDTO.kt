package com.nyakshoot.storageservice.data.dto.shipment

import com.google.gson.annotations.SerializedName
import com.nyakshoot.storageservice.data.dto.position.PositionDTO
import okhttp3.MultipartBody

data class ShipmentCreateRequestDTO(
    @SerializedName("photos")
    val photos: List<MultipartBody.Part>,
    @SerializedName("new_positions")
    val positions: List<PositionDTO>,
    @SerializedName("supplier_name")
    val supplierName: String,
    @SerializedName("document_number")
    val documentNumber: String,
    @SerializedName("delivery_man")
    val deliveryMan: String,
    @SerializedName("storage_man")
    val storageMan: String,
)
