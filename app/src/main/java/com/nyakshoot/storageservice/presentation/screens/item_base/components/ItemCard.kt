package com.nyakshoot.storageservice.presentation.screens.item_base.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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

@Composable
fun ItemCard(itemName: String, itemNumber: Int, cost: Number, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
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
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray, shape = CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = "Наименование: $itemName", fontSize = 12.sp)
                Text(text = "Артикул: $itemNumber", fontSize = 12.sp)
                Text(text = "Категория: ", fontSize = 12.sp)
            }
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "img4",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(50.dp)
            )
        }
    }
}