package com.nyakshoot.storageservice.data.dto.photo

import com.google.gson.annotations.SerializedName

data class PhotoResponseDTO(
    val id: Int,
    @SerializedName("photo_name")
    val photoName: String,
    @SerializedName("photo_bucket_name")
    val photoBucketName: String,
    @SerializedName("shipment_id")
    val shipmentId: Int,
)
