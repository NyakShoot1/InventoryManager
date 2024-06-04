package com.nyakshoot.storageservice.data.remote.clients

import com.nyakshoot.storageservice.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IDocumentClient {

    @GET("${AppConstants.BASE_URL}document/{document_id}/document_url/")
    suspend fun getDocumentUrl(@Path("document_id") documentId: Int): Response<String>

}