package com.nyakshoot.storageservice.presentation.screens.receiving_goods.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.R

@Composable
fun ItemDataCard(
    itemNumber: String,
    itemName: String,
    itemQuantity: Int,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color(0xFFF2F2F2)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("№$itemNumber", style = MaterialTheme.typography.h6)
                Text(itemName, style = MaterialTheme.typography.body1)
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {
                        onDeleteClick()
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash), //
                        contentDescription = "Delete Item",
                        tint = Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text("Шт. $itemQuantity", style = MaterialTheme.typography.body1)
            }
        }
    }
}