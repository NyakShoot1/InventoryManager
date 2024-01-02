package com.nyakshoot.storageservice.presentation.screens.profile

import com.nyakshoot.storageservice.presentation.screens.profile.models.User
import com.nyakshoot.storageservice.utils.AppConstants.BASE_AVATAR_SOURCE
import com.nyakshoot.storageservice.utils.AppConstants.BASE_USER_NAME

data class ProfileState(
    val user: User = User(BASE_AVATAR_SOURCE, BASE_USER_NAME)
)
