package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.photo.PhotoResponseDTO
import com.nyakshoot.storageservice.utils.Resource
import okhttp3.MultipartBody

interface IPhotoRepository {
    suspend fun createPhotos(photos: List<MultipartBody.Part>): Resource<List<PhotoResponseDTO>>

}