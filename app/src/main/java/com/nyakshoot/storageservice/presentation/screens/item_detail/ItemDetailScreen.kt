package com.nyakshoot.storageservice.presentation.screens.item_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.nyakshoot.storageservice.data.dto.item.ItemDTO

@Composable
fun ItemDetailScreen(item: ItemDTO) {
    Text(text = item.toString())
}