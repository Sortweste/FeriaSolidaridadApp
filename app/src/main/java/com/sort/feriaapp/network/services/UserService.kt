package com.sort.feriaapp.network.services

import com.sort.feriaapp.network.dtos.LoginDTO
import com.sort.feriaapp.network.dtos.UserDTO
import com.sort.feriaapp.network.responses.EventResponse
import com.sort.feriaapp.network.responses.MessageResponse
import com.sort.feriaapp.network.responses.TokenResponse
import retrofit2.http.*

interface UserService {

    @POST("/login")
    suspend fun login(@Body loginDTO: LoginDTO): TokenResponse

    @POST("/user")
    suspend fun register(@Body userDTO: UserDTO): TokenResponse

    @POST("/user/{eventId}/event")
    suspend fun suscribe(@Path("eventId") eventId: String, @Header("Authorization") token: String): MessageResponse

    @DELETE("/user/{eventId}/event")
    suspend fun unsuscribe(@Path("eventId") eventId: String, @Header("Authorization") token: String): MessageResponse

    @GET("/user/events")
    suspend fun getAttendEvent(@Header("Authorization") token: String): EventResponse

}