package com.nyakshoot.storageservice.data.dto.movement

import com.google.gson.annotations.SerializedName

data class NewMovementRequestDTO(
    @SerializedName("out") val from: Int,
    @SerializedName("where") val where: Int,
    @SerializedName("type") val type: Boolean,
    @SerializedName("user_id") val userId: Int
)
