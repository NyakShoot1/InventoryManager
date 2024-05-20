package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.auth.AuthRequestDTO
import com.nyakshoot.storageservice.data.dto.auth.AuthResponseDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IAuthClient
import com.nyakshoot.storageservice.domain.repository.IAuthRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val iAuthClient: IAuthClient
) : AbstractBaseClient(), IAuthRepository {

    override suspend fun authorize(loginRequestDTO: AuthRequestDTO): Resource<AuthResponseDTO> {
        return safeApiCall {iAuthClient.login(loginRequestDTO.login, loginRequestDTO.password) }
    }

}