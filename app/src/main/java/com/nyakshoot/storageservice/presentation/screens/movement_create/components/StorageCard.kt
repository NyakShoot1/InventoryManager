package com.nyakshoot.storageservice.presentation.screens.movement_create.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.data.dto.storage.StorageDTO

@Composable
fun StorageCard(
    storage: StorageDTO,
    isSelected: Boolean,
    onSelected: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(!isSelected) },
        backgroundColor = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.3f) else MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Склад №${storage.id}",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Адрес: ${storage.address}",
                style = MaterialTheme.typography.body1
            )
        }
    }
}