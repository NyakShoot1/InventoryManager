package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.data.dto.supplier.SupplierDTO
import com.nyakshoot.storageservice.utils.Resource

interface ISupplierRepository {
    suspend fun getSuppliers(): Resource<List<SupplierDTO>>
}