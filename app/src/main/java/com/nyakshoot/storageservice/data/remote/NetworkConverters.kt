package com.nyakshoot.storageservice.data.remote

import com.nyakshoot.storageservice.data.dto.user.UserDTO
import com.nyakshoot.storageservice.presentation.screens.profile.models.User
import com.nyakshoot.storageservice.utils.AppConstants.BASE_AVATAR_SOURCE
import com.nyakshoot.storageservice.utils.AppConstants.BASE_USER_NAME

fun UserDTO?.toUser(): User {
    var nonNullAvatarSource = ""
    var nonNullUserName = ""
    val tempAvatarLink = this?.avatarLink
    val tempUserName = this?.userName
    nonNullAvatarSource = if(tempAvatarLink?.equals(null) == true) {
        BASE_AVATAR_SOURCE
    }
    else {
        tempAvatarLink
    }
    nonNullUserName = if(tempUserName?.equals(null) == true) {
        BASE_USER_NAME
    }
    else {
        tempUserName
    }
    return User(nonNullAvatarSource, nonNullUserName)
}