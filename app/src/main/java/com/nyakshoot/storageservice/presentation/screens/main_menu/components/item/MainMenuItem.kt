package com.nyakshoot.storageservice.presentation.screens.main_menu.components.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuItem(item: MainMenuItemModel) {
    Card(
        modifier = Modifier
            .clickable { item.onClickStart() }
            .padding(8.dp)
            .height(130.dp)
            .width(130.dp),
        shape = RoundedCornerShape(10.dp),
        contentColor = MaterialTheme.colors.primary,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(70.dp),
                painter = painterResource(id = item.iconId),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
            Text(
                modifier = Modifier.padding(start = 2.dp, end = 2.dp), // НАДО ?
                text = stringResource(id = item.label),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondary,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}