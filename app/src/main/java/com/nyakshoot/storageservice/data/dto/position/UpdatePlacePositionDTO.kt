package com.nyakshoot.storageservice.data.dto.position

import com.google.gson.annotations.SerializedName

data class UpdatePlacePositionDTO(
    @SerializedName("position_id")
    val positionId: Int,
    @SerializedName("storage_id")
    val storageId: Int,
    @SerializedName("place_code")
    val placeCode: Char,
    @SerializedName("place_number")
    val placeNumber: Int,
)
