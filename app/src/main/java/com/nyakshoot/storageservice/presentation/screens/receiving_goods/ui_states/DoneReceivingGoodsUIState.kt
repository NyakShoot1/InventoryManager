package com.nyakshoot.storageservice.presentation.screens.receiving_goods.ui_states

import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class DoneReceivingGoodsUIState (
    val supplier: String = "",
    val documentNumber: Int = 0,
    val deliveryMan: String = "",
    val date: String = getCurrentDateTime(),
    val storageAddress: String = "",
    val items: List<ItemDTO> = listOf(),
)

fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date())
}