package com.nyakshoot.storageservice.data.dto.supplier

import com.google.gson.annotations.SerializedName

data class SupplierDTO(
    //TODO add Имя компании поставщика
    val id: Int,
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("address")
    val address: String? = "",
    @SerializedName("phone_number")
    val phoneNumber: String? = "",
    @SerializedName("status")
    val status: Boolean
)