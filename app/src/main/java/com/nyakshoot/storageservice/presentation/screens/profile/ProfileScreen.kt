package com.nyakshoot.storageservice.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.nyakshoot.storageservice.presentation.navigation.Screen

@Composable

fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()) {

    val meState by viewModel.meState.collectAsState() // состояние профиля

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Box(modifier = Modifier
            .size(120.dp)
            .graphicsLayer {
                clip = true
                shape = CircleShape
            }
            .background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center

        ) {
            Image(
                painter = rememberImagePainter(viewModel.profileState.user.avatarLink),
                contentDescription = "xd",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = viewModel.profileState.user.userName, fontSize = 28.sp, textAlign = TextAlign.Center)
        Text("тут будет просто офигеть как много разной информации, о почте, дате регистрации и прочем")
        Spacer(modifier = Modifier.fillMaxHeight(0.9f))
        Button(onClick = {
            viewModel.exit()
            navController.navigate(Screen.Login.route) {
            popUpTo(Screen.MainMenu.route) {
                inclusive = true
            }
        } }) {
            Text(text = "Выйти", fontSize = 28.sp, color = MaterialTheme.colors.secondary)
        }
    }

}