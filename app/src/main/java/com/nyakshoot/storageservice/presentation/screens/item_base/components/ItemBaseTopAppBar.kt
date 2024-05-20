package com.nyakshoot.storageservice.presentation.screens.item_base.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.storageservice.R

@Preview
@Composable
fun ItemBaseTopAppBar(

) {
    TopAppBar(
        modifier = Modifier.height(70.dp),
        title = {
            TextField(
                value = "",
                onValueChange = {

                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
                    .background(
                        color = Color(0xFFF2F2F2),
                        shape = RoundedCornerShape(10.dp)
                    ),

                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Поиск",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.baseline_filter_alt_24),
                contentDescription = null
            )
        },
    )
}