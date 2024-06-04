package com.nyakshoot.storageservice.presentation.screens.done_shipment_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.data.dto.shipment.ShipmentDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundedInfoBox(shipment: ShipmentDTO) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.12f)),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ListItem(
            modifier = Modifier.padding(8.dp),
            icon = {
                Icon(
                    imageVector = Icons.Default.LocalShipping,
                    contentDescription = "Иконка поставки",
                    tint = MaterialTheme.colors.primary
                )
            },
            text = {
                Text(
                    "Поставка #${shipment.id}",
                    style = MaterialTheme.typography.h6
                )
            },
            secondaryText = {
                val dateTimeString = parseDateTime(shipment.registerAt)
                Column {
                    Text(
                        "Дата регистрации: ${dateTimeString.first} ${dateTimeString.second}",
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        "ID ответственного: ${shipment.userId}",
                        style = MaterialTheme.typography.body2
                    )
                }
            },
            singleLineSecondaryText = false,
            overlineText = {
                Text(
                    "Информация",
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            },
            trailing = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Завершено",
                    tint = MaterialTheme.colors.primary
                )
            }
        )
    }
}

fun parseDateTime(dateTimeString: String): Pair<String, String> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)

    val formattedDate = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))

    return Pair(formattedDate, formattedTime)
}