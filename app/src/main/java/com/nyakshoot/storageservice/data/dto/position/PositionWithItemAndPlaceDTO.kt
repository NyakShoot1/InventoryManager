package com.nyakshoot.storageservice.data.dto.position

import com.google.gson.annotations.SerializedName
import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.data.dto.place.PlaceDTO

data class PositionWithItemAndPlaceDTO(
    val id: Int? = null,
    @SerializedName("item")
    val item: ItemDTO,
    @SerializedName("place")
    val place: PlaceDTO? = null,
    @SerializedName("count")
    val count: Int? = null
)
