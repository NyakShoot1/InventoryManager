package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IPositionClient
import com.nyakshoot.storageservice.domain.repository.IPositionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PositionRepositoryImpl@Inject constructor(
    private val iPositionClient: IPositionClient
): AbstractBaseClient(), IPositionRepository {
}