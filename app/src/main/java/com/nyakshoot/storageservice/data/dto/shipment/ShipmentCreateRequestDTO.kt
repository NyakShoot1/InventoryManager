package com.nyakshoot.storageservice.data.dto.shipment

import com.google.gson.annotations.SerializedName
import com.nyakshoot.storageservice.data.dto.position.PositionRequestDTO

data class ShipmentCreateRequestDTO(
    @SerializedName("photos_id")
    val photos: List<Int>,
    @SerializedName("new_positions")
    val positions: List<PositionRequestDTO>,
    @SerializedName("supplier_name")
    val supplierName: String,
    @SerializedName("document_number")
    val documentNumber: String,
    @SerializedName("delivery_man")
    val deliveryMan: String,
    @SerializedName("storage_man")
    val storageMan: String,
)
