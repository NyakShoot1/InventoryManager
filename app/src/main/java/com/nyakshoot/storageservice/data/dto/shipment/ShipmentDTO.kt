package com.nyakshoot.storageservice.data.dto.shipment

import com.google.gson.annotations.SerializedName
import com.nyakshoot.storageservice.data.dto.photo.PhotoResponseDTO

data class ShipmentDTO(
    val id: Int,
    @SerializedName("register_at")
    val registerAt: String,
    @SerializedName("document_id")
    val documentId: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("photos")
    val photos: List<PhotoResponseDTO>,
)
