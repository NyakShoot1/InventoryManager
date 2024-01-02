package com.nyakshoot.storageservice.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nyakshoot.storageservice.R
import com.nyakshoot.storageservice.presentation.navigation.Screen
import com.nyakshoot.storageservice.utils.State

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val authState by viewModel.authState.collectAsState() // состояние авторизации
    val needAuthState by viewModel.needAuth.collectAsState() // проверка, имеется ли токены (переместить в сплэш скрин и сделать норм проверку по токенам)

    LaunchedEffect(needAuthState.status) {
        viewModel.checkTokens()
        when(needAuthState.status) {
            State.Status.SUCCESS -> {
                navController.navigate(Screen.MainMenu.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
            }
            State.Status.ERROR -> {}
            State.Status.LOADING -> {}
        }
    }

    when (authState.status) {
        State.Status.SUCCESS -> {
            navController.navigate(Screen.MainMenu.route) {
                popUpTo(Screen.Login.route) {
                    inclusive = true
                }
            }
            viewModel.updateAuthState()
        }
        State.Status.ERROR -> {
            Toast.makeText(context, authState.message, Toast.LENGTH_SHORT).show()
            viewModel.updateAuthState()
        }

        State.Status.LOADING -> {}
    }

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
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Вход",
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = viewModel.loginState.errorMessageInput?: "", color = Color.Red)


        TextField(value = viewModel.loginState.loginInput, onValueChange = { viewModel.onEmailInputChange(it) },
            placeholder = {
                Text(
                    text = "Логин",
                    color = MaterialTheme.colors.primary
                )
            })

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = viewModel.loginState.passwordInput,
            onValueChange = { viewModel.onPasswordInputChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = {
                Text(
                    text = "Пароль",
                    color = MaterialTheme.colors.primary
                )
            },
            trailingIcon = {
                if(viewModel.loginState.isPasswordShown) {
                    Icon(
                        imageVector = Icons.Outlined.RemoveRedEye,
                        contentDescription = "скрыть пароль",
                        modifier = Modifier.clickable { viewModel.onToggleVisualTransformation() }
                    )
                }
                else {
                    Icon(
                        imageVector = Icons.Outlined.VisibilityOff,
                        contentDescription = "показать пароль",
                        modifier = Modifier.clickable { viewModel.onToggleVisualTransformation() }
                    )
                }
            },
            visualTransformation = if(viewModel.loginState.isPasswordShown) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .height(65.dp)
                .width(245.dp),
            onClick = {
                viewModel.authorize(viewModel.loginState.loginInput, viewModel.loginState.passwordInput)

            }) {
            Text(
                text = "Войти",
                fontSize = 24.sp,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

