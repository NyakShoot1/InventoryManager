package com.nyakshoot.storageservice.data.remote.base_response_wrapper
import com.nyakshoot.storageservice.utils.Resource
import retrofit2.Response

abstract class AbstractBaseClient {

    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.code() == 200) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return Resource.error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Resource.error("Ошибка: ${e.message}")
        }
    }
}