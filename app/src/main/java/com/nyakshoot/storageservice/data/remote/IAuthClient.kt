package com.nyakshoot.storageservice.data.remote

import com.nyakshoot.storageservice.data.dto.auth.AuthRequestDTO
import com.nyakshoot.storageservice.data.dto.auth.AuthResponseDTO
import com.nyakshoot.storageservice.utils.AppConstants.BASE_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface IAuthClient {

    @POST("${BASE_URL}test/login")
    suspend fun login(@Body authRequest: AuthRequestDTO): Response<AuthResponseDTO>

}