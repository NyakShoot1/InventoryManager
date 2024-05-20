package com.nyakshoot.storageservice.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.storageservice.data.local.TokenManager
import com.nyakshoot.storageservice.data.remote.toUser
import com.nyakshoot.storageservice.domain.repository.IUserRepository
import com.nyakshoot.storageservice.presentation.screens.profile.models.User
import com.nyakshoot.storageservice.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val iUserRepository: IUserRepository,
    private val tokenManager: TokenManager
): ViewModel() {

    // для выполнения авторизации
    private val _meResource = MutableStateFlow<Resource<User>>(Resource.loading(null))
    val meResource: StateFlow<Resource<User>> = _meResource

    var profileState by mutableStateOf(ProfileState())
        private set
    init {
        getUserInfo()
    }

    private fun getUserInfo() = viewModelScope.launch {
        val meResponse = iUserRepository.getMe()
        if(meResponse.status == Resource.Status.SUCCESS) {
            val nonNullUser = meResponse.data?.toUser() ?: return@launch
            _meResource.value = Resource(meResponse.status, nonNullUser, meResponse.message)
            profileState = profileState.copy(user = nonNullUser)
        }
    }

    fun exit() = viewModelScope.launch {
        tokenManager.setAccessToken("")
        tokenManager.setRefreshToken("")
    }
}
