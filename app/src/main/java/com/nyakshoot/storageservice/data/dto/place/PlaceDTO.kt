package com.nyakshoot.storageservice.data.dto.place

import com.google.gson.annotations.SerializedName

data class PlaceDTO(
    @SerializedName("storage_id")
    val storageId: Int? = null,
    @SerializedName("place_code")
    val placeCode: Char,
    @SerializedName("place_number")
    val placeNumber: Int,
    @SerializedName("busy")
    val busy: Boolean,
)
