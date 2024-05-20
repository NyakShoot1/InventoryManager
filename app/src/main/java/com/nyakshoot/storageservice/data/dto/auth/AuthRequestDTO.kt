package com.nyakshoot.storageservice.data.dto.auth

import com.google.gson.annotations.SerializedName

data class AuthRequestDTO (
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)
