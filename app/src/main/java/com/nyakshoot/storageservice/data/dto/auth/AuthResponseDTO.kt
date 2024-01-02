package com.nyakshoot.storageservice.data.dto.auth

import com.google.gson.annotations.SerializedName

data class AuthResponseDTO (
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)