package com.nyakshoot.storageservice.data.dto.position

import com.google.gson.annotations.SerializedName

data class PositionDTO(
    val id: Int? = null,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("item_id")
    val itemId: Int?,
    @SerializedName("place_id")
    val placeId: Int? = null
)