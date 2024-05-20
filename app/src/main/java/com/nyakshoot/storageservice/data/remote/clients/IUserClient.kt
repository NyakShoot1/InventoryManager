package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.user.UserDTO
import com.nyakshoot.storageservice.utils.AppConstants.BASE_URL
import retrofit2.Response
import retrofit2.http.GET

interface IUserClient {
    @GET("${BASE_URL}test/me")
    suspend fun getMe(): Response<UserDTO>
}