package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IItemClient
import com.nyakshoot.storageservice.domain.repository.IItemRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImpl @Inject constructor(
    private val iItemClient: IItemClient
) : AbstractBaseClient(), IItemRepository {
    override suspend fun getItems(): Resource<List<ItemDTO>> {
        return safeApiCall { iItemClient.getItems() }
    }

    override suspend fun getItemByBarcode(barcode: String): Resource<ItemDTO> {
        return safeApiCall { iItemClient.getItemByBarcode(barcode) }
    }

}