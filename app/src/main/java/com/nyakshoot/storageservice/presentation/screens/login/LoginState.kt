package com.nyakshoot.storageservice.presentation.screens.login

data class LoginState(
    val loginInput: String = "",
    val passwordInput: String = "",
    val isSuccessfullyLoggedIn: Boolean = false,
    val errorMessageLoginProcess: String? = null,
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val errorMessageInput: String? = null
)
