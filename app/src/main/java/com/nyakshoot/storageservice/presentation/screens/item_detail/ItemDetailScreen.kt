package com.nyakshoot.storageservice.presentation.screens.item_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.data.dto.item.ItemDTO

@Composable
fun ItemDetailScreen(item: ItemDTO) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold
            )

            if (item.barcode != null) {
                Text(
                    text = "Штрих-код: ${item.barcode}",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            DetailSection(title = "Цена") {
                DetailItem("Стоимость:", item.cost?.toString() ?: "Н/Д", "руб.")
                DetailItem("Закупочная цена:", item.purchasePrice?.toString() ?: "Н/Д", "руб.")
            }

            if (item.description != null) {
                DetailSection(title = "Описание") {
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            DetailSection(title = "Информация о товаре") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DetailItem("Страна:", item.country ?: "Н/Д")
                    DetailItem("Номер:", item.itemNumber.toString() ?: "Н/Д")
                }
            }

            DetailSection(title = "Физические характеристики") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DetailItem("Вес:", item.weight?.toString() ?: "Н/Д", "кг")
                    DetailItem("Объем:", item.volume?.toString() ?: "Н/Д", "см³")
                }
            }

            if (item.unit != null) {
                DetailSection(title = "Единица измерения") {
                    Text(
                        text = item.unit,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun DetailSection(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            color = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String, unit: String? = null) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = buildString {
                append(value)
                unit?.let { append(" $it") }
            },
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}