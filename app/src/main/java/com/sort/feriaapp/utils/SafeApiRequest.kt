package com.sort.feriaapp.utils

import retrofit2.Response
import java.io.IOException

/*Display information if Status is SUCCESS*/
abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Resource<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            Resource.success(response.body()!!)
        else
            //error(response.message(), response.code())
            error(response.code())
    }

    private fun <T> error(code: Int): Resource<T> {
        var message = ""
        when(code){
            404 -> {message = "Usuario no encontrado"}
            400 -> {message = "Credenciales no válidas"}
            500 -> {message = "Error al iniciar sesión"}
        }
        return Resource.error(message)
    }

}
