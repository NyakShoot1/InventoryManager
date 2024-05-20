package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.auth.AuthRequestDTO
import com.nyakshoot.storageservice.data.dto.auth.AuthResponseDTO
import com.nyakshoot.storageservice.utils.Resource

interface IAuthRepository {
    suspend fun authorize(loginRequestDTO: AuthRequestDTO): Resource<AuthResponseDTO>
}