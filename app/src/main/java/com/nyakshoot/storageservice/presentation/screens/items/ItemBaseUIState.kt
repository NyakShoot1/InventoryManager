package com.nyakshoot.storageservice.presentation.screens.items

import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.utils.Resource

data class ItemBaseUIState(
    val isLoading: Boolean = false,
    val items: Resource<List<ItemDTO>> = Resource.loading()
)