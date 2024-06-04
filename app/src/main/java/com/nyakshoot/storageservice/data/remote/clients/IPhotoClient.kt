package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.photo.PhotoResponseDTO
import com.nyakshoot.storageservice.utils.AppConstants
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface IPhotoClient {

    @Multipart
    @POST("${AppConstants.BASE_URL}photo/create_photo/")
    suspend fun createPhoto(
        @Part photos: List<MultipartBody.Part>,
    ): Response<PhotoResponseDTO>


    @GET("${AppConstants.BASE_URL}photo/{photo_id}/photo_url/")
    suspend fun getPhotoURL(
        @Path("photo_id") photoId: Int
    ): Response<String>
}