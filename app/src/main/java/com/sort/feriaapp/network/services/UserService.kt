package com.sort.feriaapp.network.services

import com.sort.feriaapp.network.dtos.LoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("/login")
    suspend fun login(@Body loginDTO: LoginDTO):

    @POST("/user")
    suspend fun register(@Body ):

    @GET("/user/events")
    suspend fun getAttendEvent(@Header("Authorization") token: String):

}