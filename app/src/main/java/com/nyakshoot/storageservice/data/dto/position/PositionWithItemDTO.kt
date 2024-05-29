package com.nyakshoot.storageservice.data.dto.position

import com.google.gson.annotations.SerializedName
import com.nyakshoot.storageservice.data.dto.item.ItemDTO

data class PositionWithItemDTO(
    @SerializedName("item_id")
    val itemId: Int,
    val count: Int,
    val item: ItemDTO
)
