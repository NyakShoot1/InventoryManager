package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.storage.StorageDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IStorageClient
import com.nyakshoot.storageservice.domain.repository.IStorageRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val iStorageClient: IStorageClient
) : AbstractBaseClient(), IStorageRepository {
    override suspend fun getStorages(): Resource<List<StorageDTO>> {
        return safeApiCall { iStorageClient.getStorages() }
    }
}