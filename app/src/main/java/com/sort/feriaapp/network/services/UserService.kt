package com.sort.feriaapp.network.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("/login")
    suspend fun login(): Response<>

    @POST("")
    suspend fun register(): Response<>

    @GET("/user/events")
    suspend fun getAttendEvent(@Header("Authorization") token: String):

}