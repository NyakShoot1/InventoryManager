package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.supplier.SupplierDTO
import com.nyakshoot.storageservice.utils.AppConstants.BASE_URL
import retrofit2.Response
import retrofit2.http.GET

interface ISupplierClient {

    @GET("${BASE_URL}supplier/suppliers")
    suspend fun getSuppliers(): Response<List<SupplierDTO>>

}