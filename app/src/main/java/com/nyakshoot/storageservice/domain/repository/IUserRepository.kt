package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.user.UserDTO
import com.nyakshoot.storageservice.utils.State

interface IUserRepository {
    suspend fun getMe(): State<UserDTO>
}