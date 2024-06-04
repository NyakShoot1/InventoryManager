package com.nyakshoot.storageservice.presentation.screens.positions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.data.dto.position.PositionWithItemAndPlaceDTO

@Composable
fun PositionCard(
    position: PositionWithItemAndPlaceDTO,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFF2F2F2)
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
                Text(text = "Наименование: ${position.item.name}", fontSize = 12.sp)
                Text(text = "Количество: ${position.count}", fontSize = 12.sp)
                Text(text = "Код места: ${position.place?.placeCode?: "-"}", fontSize = 12.sp)
                Text(text = "Номер места: ${position.place?.placeNumber?: "-"}", fontSize = 12.sp)
            }
            if (position.place == null)
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_home_24),
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(50.dp)
                        .clickable { onClick() }
                )
        }
    }
}