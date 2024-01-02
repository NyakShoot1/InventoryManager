package com.nyakshoot.storageservice.presentation.screens.profile.models

import com.nyakshoot.storageservice.utils.AppConstants.BASE_AVATAR_SOURCE
import com.nyakshoot.storageservice.utils.AppConstants.BASE_USER_NAME

data class User (
    val avatarLink: String = BASE_AVATAR_SOURCE,
    val userName: String = BASE_USER_NAME
)