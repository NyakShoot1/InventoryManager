package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.user.UserDTO
import com.nyakshoot.storageservice.data.remote.IUserClient
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.domain.repository.IUserRepository
import com.nyakshoot.storageservice.utils.State
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val iUserClient: IUserClient
) : AbstractBaseClient(), IUserRepository {
    override suspend fun getMe(): State<UserDTO> {
        return safeApiCall { iUserClient.getMe() }
    }

}




