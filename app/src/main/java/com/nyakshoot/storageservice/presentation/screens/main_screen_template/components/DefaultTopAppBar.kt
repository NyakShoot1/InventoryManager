package com.nyakshoot.storageservice.presentation.screens.main_screen_template.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.R

@Composable
fun DefaultTopAppBar(navController: NavHostController, title: String) {

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        TopAppBar(
            modifier = Modifier,
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.backspace),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary
                )
            },
            title = {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    textAlign = TextAlign.Start,
                    text = title,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            contentColor = MaterialTheme.colors.primary
        )
    }
}