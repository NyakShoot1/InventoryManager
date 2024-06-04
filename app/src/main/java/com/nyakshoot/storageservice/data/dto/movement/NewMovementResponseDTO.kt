package com.nyakshoot.storageservice.data.dto.movement

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NewMovementResponseDTO(
    @SerializedName("out") val from: Int,
    @SerializedName("where") val where: Int,
    @SerializedName("type") val type: Boolean,
    @SerializedName("status") val status: Boolean,
    @SerializedName("register_at") val registerAt: Date,
    @SerializedName("document_id") val documentId: Int,
    @SerializedName("user_id") val userId: Int
)
