package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.user.UserDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IUserClient
import com.nyakshoot.storageservice.domain.repository.IUserRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val iUserClient: IUserClient
) : AbstractBaseClient(), IUserRepository {
    override suspend fun getMe(): Resource<UserDTO> {
        return safeApiCall { iUserClient.getMe() }
    }

}




