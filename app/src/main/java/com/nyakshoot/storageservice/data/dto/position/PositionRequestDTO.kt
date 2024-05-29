package com.nyakshoot.storageservice.data.dto.position

import com.google.gson.annotations.SerializedName

data class PositionRequestDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("item_id")
    val itemId: Int,
)
