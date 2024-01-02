package com.nyakshoot.storageservice.data.dto.user

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: Int,
    @SerializedName("avatar_link")
    val avatarLink: String? = "",
    @SerializedName("user_name")
    val userName: String? = ""
)
