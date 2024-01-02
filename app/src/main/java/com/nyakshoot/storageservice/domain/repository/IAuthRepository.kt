package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.auth.AuthRequestDTO
import com.nyakshoot.storageservice.data.dto.auth.AuthResponseDTO
import com.nyakshoot.storageservice.utils.State

interface IAuthRepository {
    suspend fun authorize(loginRequestDTO: AuthRequestDTO): State<AuthResponseDTO>
}