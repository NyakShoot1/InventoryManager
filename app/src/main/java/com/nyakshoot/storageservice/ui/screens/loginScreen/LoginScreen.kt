package com.nyakshoot.storageservice.ui.screens.loginScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.ui.navigation.Screen
import com.nyakshoot.storageservice.ui.navigation.rememberNavigationState
import com.nyakshoot.storageservice.utils.currentRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginClickListener: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(id = R.drawable.box),
            contentDescription = "box"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Inventory Manager",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Вход",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        var login by remember { mutableStateOf("") }
        TextField(value = login, onValueChange = { login = it },
            placeholder = {
                Text(
                    text = "Логин",
                    color = MaterialTheme.colorScheme.primary
                )
            })

        Spacer(modifier = Modifier.height(20.dp))

        var password by remember { mutableStateOf("") }
        TextField(
            value = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = "Пароль",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .height(65.dp)
                .width(245.dp),
            onClick = {
                onLoginClickListener()
            }) {
            Text(
                text = "Войти",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

