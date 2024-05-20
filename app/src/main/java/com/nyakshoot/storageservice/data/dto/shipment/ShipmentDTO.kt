package com.nyakshoot.storageservice.data.dto.shipment

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ShipmentDTO(
    val id: Int,
    @SerializedName("register_at")
    val registerAt: LocalDateTime,
    @SerializedName("document_id")
    val documentId: Int,
    @SerializedName("user_id")
    val userId: Int,
)
