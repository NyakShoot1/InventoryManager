package com.nyakshoot.storageservice.presentation.screens.receiving_goods.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TakePhotoItem(text: String, photo: Int = -1, onAddPhotoClick: (() -> Unit),) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(75.dp)
            .width(250.dp)
            .padding(10.dp)
            .clickable(
                onClick = onAddPhotoClick,
                indication = null, 
                interactionSource = remember { MutableInteractionSource() }
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text)
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = photo),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        }
    }
}