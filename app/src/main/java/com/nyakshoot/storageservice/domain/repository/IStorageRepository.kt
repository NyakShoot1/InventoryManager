package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.storage.StorageDTO
import com.nyakshoot.storageservice.utils.Resource

interface IStorageRepository {
    suspend fun getStorages(): Resource<List<StorageDTO>>
}