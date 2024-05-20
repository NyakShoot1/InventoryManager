package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.dto.supplier.SupplierDTO
import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.ISupplierClient
import com.nyakshoot.storageservice.domain.repository.ISupplierRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupplierRepositoryImpl @Inject constructor(
    private val iSupplierClient: ISupplierClient
) : AbstractBaseClient(), ISupplierRepository {
    override suspend fun getSuppliers(): Resource<List<SupplierDTO>> {
        return safeApiCall { iSupplierClient.getSuppliers() }
    }

}