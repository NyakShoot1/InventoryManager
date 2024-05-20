package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.photo.PhotoResponseDTO
import com.nyakshoot.storageservice.utils.AppConstants
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IPhotoClient {

    @Multipart
    @POST("${AppConstants.BASE_URL}photo/create_photos/")
    suspend fun createPhotos(
        @Part photos: List<MultipartBody.Part>,
    ): Response<List<PhotoResponseDTO>>

}