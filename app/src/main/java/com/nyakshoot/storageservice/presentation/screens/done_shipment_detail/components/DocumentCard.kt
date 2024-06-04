package com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.utils.Resource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DocumentCard(
    state: Resource.Status,
    onDownload: () -> Unit
) {
    Card(
        onClick = onDownload,
        enabled = state == Resource.Status.SUCCESS,
        backgroundColor = if (state == Resource.Status.SUCCESS) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        ListItem(
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = "Иконка документа",
                    tint = MaterialTheme.colors.onSurface
                )
            },
            text = {
                Text(
                    "Информация о поставке",
                    style = MaterialTheme.typography.subtitle1
                )
            },
            secondaryText = {
                Text(
                    when (state) {
                        Resource.Status.LOADING -> "Загрузка..."
                        Resource.Status.SUCCESS -> "Готов к скачиванию"
                        Resource.Status.ERROR -> "Ошибка: $state"
                    },
                    style = MaterialTheme.typography.body2
                )
            },
            singleLineSecondaryText = false,
            overlineText = {
                if (state == Resource.Status.SUCCESS) {
                    Text(
                        "Документ доступен",
                        style = MaterialTheme.typography.overline,
                        color = MaterialTheme.colors.primary
                    )
                }
            },
            trailing = {
                when (state) {
                    Resource.Status.LOADING -> CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Resource.Status.SUCCESS -> Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Скачать",
                        tint = MaterialTheme.colors.primary
                    )

                    Resource.Status.ERROR -> Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Ошибка",
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )
    }
}