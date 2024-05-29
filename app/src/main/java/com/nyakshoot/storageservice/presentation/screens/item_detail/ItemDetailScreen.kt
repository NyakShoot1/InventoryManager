package com.nyakshoot.storageservice.presentation.screens.item_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.data.dto.item.ItemDTO

@Composable
fun ItemDetailScreen(item: ItemDTO) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary
            )
            if (item.barcode != null) {
                Text(
                    text = item.barcode,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Стоимость: ${item.cost ?: "Н/Д"} руб.",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = "Закупочная цена: ${item.purchasePrice ?: "Н/Д"} руб.",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (item.description != null) {
            Text(
                text = "Описание: ${item.description}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Страна: ${item.country ?: "Н/Д"}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = "Номер: ${item.itemNumber ?: "Н/Д"}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Вес: ${item.weight ?: "Н/Д"} кг",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = "Объем: ${item.volume ?: "Н/Д"} см³",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
        }

        if (item.unit != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Единица измерения: ${item.unit}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}