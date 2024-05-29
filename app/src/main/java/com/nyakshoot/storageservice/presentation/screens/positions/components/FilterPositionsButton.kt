package com.nyakshoot.storageservice.presentation.screens.positions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterPositionsButton(
    filterText: String,
    isSelected: Boolean,
    onFilterSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MaterialTheme.colors.primary else Color.LightGray)
            .clickable { onFilterSelected(filterText) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = filterText,
            color = if (isSelected) MaterialTheme.colors.secondary else Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}