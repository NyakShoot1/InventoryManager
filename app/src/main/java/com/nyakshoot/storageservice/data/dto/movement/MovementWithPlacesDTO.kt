package com.nyakshoot.storageservice.data.dto.movement

import com.google.gson.annotations.SerializedName

data class MovementWithPlacesDTO (
    val id: Int,
    @SerializedName("out") val fromPlace: FromPlaceDTO,
    @SerializedName("where") val where: WhereDTO?,
    @SerializedName("type") val type: Boolean,
    @SerializedName("status") val status: Boolean,
    @SerializedName("register_at") val registerAt: String,
    @SerializedName("document_id") val documentId: Int?,
    @SerializedName("user_id") val userId: Int
)

data class FromPlaceDTO(
    @SerializedName("place_code") val placeCode: Char,
    @SerializedName("place_number") val placeNumber: Int,
    val item: MovementItemDTO?
)

interface WhereDTO {
    val displayText: String
}

data class PlaceDTO(
    @SerializedName("place_code") val placeCode: Char,
    @SerializedName("place_number") val placeNumber: Int
) : WhereDTO {
    override val displayText: String
        get() = "$placeCode$placeNumber"
}

data class StorageDTO(
    @SerializedName("address") val address: String
) : WhereDTO {
    override val displayText: String
        get() = "Склад: $address"
}

data class MovementItemDTO(
    val name: String
)