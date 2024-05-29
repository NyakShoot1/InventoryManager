package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.storage.StorageDTO
import com.nyakshoot.storageservice.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET

interface IStorageClient {
    @GET("${AppConstants.BASE_URL}storage/storages/")
    suspend fun getStorages(): Response<List<StorageDTO>>
}