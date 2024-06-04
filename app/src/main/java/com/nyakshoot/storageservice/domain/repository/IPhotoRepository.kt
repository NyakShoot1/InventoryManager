package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.photo.PhotoResponseDTO
import com.nyakshoot.storageservice.utils.Resource
import okhttp3.MultipartBody

interface IPhotoRepository {
    suspend fun createPhoto(photo: List<MultipartBody.Part>): Resource<PhotoResponseDTO>

    suspend fun getPhotoURL(photoId: Int): Resource<String>
}