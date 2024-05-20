package com.nyakshoot.storageservice.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.dto.auth.AuthRequestDTO
import com.nyakshoot.storageservice.data.dto.auth.AuthResponseDTO
import com.nyakshoot.storageservice.data.local.TokenManager
import com.nyakshoot.storageservice.domain.model.LoginInputValidationType
import com.nyakshoot.storageservice.domain.repository.IAuthRepository
import com.nyakshoot.storageservice.domain.use_case.ValidateLoginInputUseCase
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val tokenManager: TokenManager,
    private val iAuthRepository: IAuthRepository
): ViewModel() {

    // для выполнения авторизации
    private val _authResource = MutableStateFlow<Resource<AuthResponseDTO>>(Resource.loading(null))
    val authResource: StateFlow<Resource<AuthResponseDTO>> = _authResource

    // проверка по токенам: нужна ли авторизация вообще
    // TODO проверка валидности токена
    private val _needAuth = MutableStateFlow<Resource<Boolean>>(Resource.loading(null))
    val needAuth: StateFlow<Resource<Boolean>> = _needAuth

    // ui стейт
    var loginState by mutableStateOf(LoginState())
        private set

    /** обновления ui стейта: показывание\сокрытие пароля*/
    fun onToggleVisualTransformation() {
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    /** изменения логина в поле для ввода логина*/
    fun onEmailInputChange(newValue: String) {
        loginState = loginState.copy(loginInput = newValue)
        checkInputValidation()
    }

    /** изменения пароля в поле для ввода пароля*/
    fun onPasswordInputChange(newValue: String) {
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }


    /** метод авторизации */
    fun authorize(login: String, password: String) = viewModelScope.launch {
        _authResource.value = Resource(Resource.Status.LOADING, null, "")
        val authInfo = AuthRequestDTO(login, password)
        val authResponse = iAuthRepository.authorize(authInfo)
        if (authResponse.status == Resource.Status.SUCCESS) {
            val nonNullAuthResponseData = authResponse.data ?: return@launch
            _authResource.value = authResponse
            setTokens(nonNullAuthResponseData)
        }
        else {
            _authResource.value = Resource(Resource.Status.ERROR, null,"Проверьте логин\\пароль")
        }
    }

    /** метод обновления состояния */
    fun updateAuthState() {
        _authResource.value = Resource(Resource.Status.LOADING, null, "")
    }

    /** метод обновления токенов досступа*/
    private fun setTokens(authResponse: AuthResponseDTO) = viewModelScope.launch {
        tokenManager.setAccessToken(authResponse.accessToken)
        tokenManager.setRefreshToken(authResponse.refreshToken)
    }


    /** метод проверки наличия токенов*/
    fun checkTokens() = viewModelScope.launch {
        val accessToken = tokenManager.getAccessToken().first() ?: ""
        if(accessToken != "") {
            _needAuth.value = Resource.success(false)
        }
        else {
            _needAuth.value = Resource.error("Необходима авторизация")
        }
    }

    /** проверка, все ли формы заполнены корректно*/
    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            loginState.loginInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    /** Ошибки валидации*/
    private fun processInputValidationType(type: LoginInputValidationType) {
        loginState = when(type) {
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessageInput = "Заполните все поля", isInputValid = false)
            }
            LoginInputValidationType.NoEmail -> {
                loginState.copy(errorMessageInput = "Проверьте корерктность почты", isInputValid = false)
            }
            LoginInputValidationType.Valid -> {
                loginState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }
}