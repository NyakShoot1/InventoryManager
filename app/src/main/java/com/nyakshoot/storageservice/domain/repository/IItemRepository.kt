package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.utils.Resource

interface IItemRepository {
    suspend fun getItems(): Resource<List<ItemDTO>>

    suspend fun getItemByBarcode(barcode: String): Resource<ItemDTO>
}