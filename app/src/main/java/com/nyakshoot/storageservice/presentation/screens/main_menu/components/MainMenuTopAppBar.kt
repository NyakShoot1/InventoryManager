package com.nyakshoot.storageservice.presentation.screens.main_menu.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.storageservice.R
import kotlinx.coroutines.launch


@Composable
fun MainMenuTopAppBar(scaffoldState: ScaffoldState) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        TopAppBar(
            modifier = Modifier,
            navigationIcon = {
                val scope = rememberCoroutineScope()
                Icon(
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                        .size(40.dp),
                    painter = painterResource(id = R.drawable.hamburger_menu),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary,

                )
            },
            title = {
                Text(
                    textAlign = TextAlign.Center,
                    text = "СкладПлюс",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            contentColor = MaterialTheme.colors.primary
            )
    }
}
