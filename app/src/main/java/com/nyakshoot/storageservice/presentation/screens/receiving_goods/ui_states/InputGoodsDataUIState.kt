package com.nyakshoot.storageservice.presentation.screens.receiving_goods.ui_states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.nyakshoot.storageservice.data.dto.item.ItemDTO

data class InputGoodsDataUIState(
    val currentItems: SnapshotStateList<ItemDTO> = mutableStateListOf(),
    val isScannerShown: MutableState<Boolean> = mutableStateOf(false),
    val isOKDialogShown: MutableState<Boolean> = mutableStateOf(false)
)
