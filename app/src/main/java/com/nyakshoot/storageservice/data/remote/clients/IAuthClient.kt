package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.auth.AuthResponseDTO
import com.nyakshoot.storageservice.utils.AppConstants.BASE_URL
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface IAuthClient {
    @FormUrlEncoded
    @POST("${BASE_URL}jwt/login/")
    suspend fun login(@Field("login") login: String,
                      @Field("password") password: String): Response<AuthResponseDTO>

}