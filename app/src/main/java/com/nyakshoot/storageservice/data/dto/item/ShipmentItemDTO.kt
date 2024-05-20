package com.nyakshoot.storageservice.data.dto.item

import com.google.gson.annotations.SerializedName

data class ShipmentItemDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cost")
    val cost: Int,
    @SerializedName("purchase_price")
    val purchasePrice: Int,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("full_item_cost")
    val fullItemCost: Int
)
