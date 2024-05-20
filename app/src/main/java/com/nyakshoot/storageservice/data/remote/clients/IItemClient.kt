package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.data.dto.item.ItemDTO
import com.nyakshoot.storageservice.utils.AppConstants.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IItemClient {
    @GET("${BASE_URL}item/items/")
    suspend fun getItems(): Response<List<ItemDTO>>

    @GET("${BASE_URL}item/barcode/{barcode}")
    suspend fun getItemByBarcode(@Path("barcode") barcode: String): Response<ItemDTO>
}