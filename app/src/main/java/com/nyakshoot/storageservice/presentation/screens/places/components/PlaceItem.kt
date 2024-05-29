package com.nyakshoot.storageservice.presentation.screens.places.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.data.dto.place.PlaceDTO

@Composable
fun PlaceItem(
    place: PlaceDTO,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                enabled = place.busy, // Отключаем клик, если место занято
                onClick = onClick
            ),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = if (place.busy) Color.LightGray else Color(0xFFF2F2F2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = "Ряд: ${place.placeCode}", fontSize = 16.sp)
                Text(text = "Номер: ${place.placeNumber}", fontSize = 16.sp)
                Text(
                    text = "Статус: ${if (place.busy) "Занято" else "Свободно"}",
                    fontSize = 16.sp,
                    color = if (place.busy) Color.Red else Color.Green
                )
            }
            if (place.busy) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_lock_24),
                    contentDescription = "Место занято",
                    tint = Color.Red
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.outline_check_circle_24),
                    contentDescription = "Место свободно",
                    tint = Color.Green
                )
            }
        }
    }
}