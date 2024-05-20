package com.nyakshoot.storageservice.data.dto.storage

import com.google.gson.annotations.SerializedName

data class StorageDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("address")
    val address: String
)
