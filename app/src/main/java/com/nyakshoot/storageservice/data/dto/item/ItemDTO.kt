package com.nyakshoot.storageservice.data.dto.item

import com.google.gson.annotations.SerializedName

data class ItemDTO(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("cost")
    val cost: Int? = null,
    @SerializedName("purchase_price")
    val purchasePrice: Int? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("item_number")
    val itemNumber: Int? = null,
    @SerializedName("wight")
    val weight: Int? = null,
    @SerializedName("volume")
    val volume: Int? = null,
    @SerializedName("barcode")
    val barcode: String? = null,
    @SerializedName("unit")
    val unit: String? = null
)