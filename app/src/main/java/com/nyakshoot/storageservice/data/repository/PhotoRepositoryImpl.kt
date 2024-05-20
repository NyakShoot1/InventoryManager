package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.photo.PhotoResponseDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IPhotoClient
import com.nyakshoot.storageservice.domain.repository.IPhotoRepository
import com.nyakshoot.storageservice.utils.Resource
import okhttp3.MultipartBody
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val iPhotoClient: IPhotoClient
) : AbstractBaseClient(), IPhotoRepository {
    override suspend fun createPhotos(photos: List<MultipartBody.Part>): Resource<List<PhotoResponseDTO>> {
        return safeApiCall { iPhotoClient.createPhotos(photos) }
    }
}