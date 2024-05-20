package com.nyakshoot.storageservice.presentation.screens.receiving_goods.ui_states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.nyakshoot.storageservice.data.dto.photo.PhotoDTO
import com.nyakshoot.storageservice.data.dto.supplier.SupplierDTO
import com.nyakshoot.storageservice.utils.Resource

data class InputDeliveryDataUIState(
    val suppliers: Resource<List<SupplierDTO>> = Resource.loading(),
    val selectedSupplier: MutableState<String> = mutableStateOf(""),
    val numberDocument: MutableState<String> = mutableStateOf(""),
    val deliveryMan: MutableState<String> = mutableStateOf(""),
    val photos: SnapshotStateList<PhotoDTO> = mutableStateListOf()
)
