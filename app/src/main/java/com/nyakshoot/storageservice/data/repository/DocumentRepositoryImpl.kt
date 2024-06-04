package com.nyakshoot.storageservice.data.repository

import com.nyakshoot.storageservice.data.remote.base_response_wrapper.AbstractBaseClient
import com.nyakshoot.storageservice.data.remote.clients.IDocumentClient
import com.nyakshoot.storageservice.domain.repository.IDocumentRepository
import com.nyakshoot.storageservice.utils.Resource
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val iDocumentClient: IDocumentClient
) : AbstractBaseClient(), IDocumentRepository {
    override suspend fun getDocumentUrl(documentId: Int): Resource<String> {
        return safeApiCall { iDocumentClient.getDocumentUrl(documentId) }
    }

}