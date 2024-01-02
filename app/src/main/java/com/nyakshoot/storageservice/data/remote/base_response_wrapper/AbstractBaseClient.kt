package com.nyakshoot.storageservice.data.remote.base_response_wrapper
import com.nyakshoot.storageservice.utils.State
import retrofit2.Response

abstract class AbstractBaseClient {

    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>): State<T> {
        try {
            val response = call()
            if (response.code() == 200) {
                val body = response.body()
                if (body != null) return State.success(body)
            }
            return State.error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return State.error("Ошибка: ${e.message}")
        }
    }
}