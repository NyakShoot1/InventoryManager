package com.nyakshoot.storageservice.presentation.screens.receiving_goods

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nyakshoot.storageservice.R

@Composable
fun InputDeliveryDataScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var provider by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = provider,
            onValueChange = { provider = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = "Поставщик",
                    color = MaterialTheme.colors.primary
                )
            }
        )

        var invoiceNumber by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = invoiceNumber,
            onValueChange = { invoiceNumber = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = "Номер накладной",
                    color = MaterialTheme.colors.primary
                )
            }
        )

        var sender by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .padding(10.dp),
            value = sender,
            onValueChange = { sender = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = "Отпуск груза разрешил",
                    color = MaterialTheme.colors.primary
                )
            }
        )

        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .height(195.dp)
                .width(275.dp)
                .background(MaterialTheme.colors.background)
        ) {
            LazyColumn() {
                item {
                    PhotoItem("Добавить фото", R.drawable.camera_add)
                }
            }
        }
    }
}

@Composable
fun PhotoItem(text: String, photo: Int = -1) {
    Card(
        modifier = Modifier
            .clickable { }
            .height(75.dp)
            .fillMaxWidth()
            .padding(10.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(10.dp)
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