package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.user.UserDTO
import com.nyakshoot.storageservice.utils.Resource

interface IUserRepository {
    suspend fun getMe(): Resource<UserDTO>
}