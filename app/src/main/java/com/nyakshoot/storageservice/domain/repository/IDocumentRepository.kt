package com.nyakshoot.storageservice.domain.repository

import com.nyakshoot.storageservice.utils.Resource

interface IDocumentRepository {

    suspend fun getDocumentUrl(documentId: Int): Resource<String>

}