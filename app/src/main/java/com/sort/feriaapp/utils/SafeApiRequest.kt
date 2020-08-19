package com.sort.feriaapp.utils

import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Resource<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            Resource.success(response.body()!!)
        else
            error("${response.code()} ${response.message()}")
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error(message)
    }

}
