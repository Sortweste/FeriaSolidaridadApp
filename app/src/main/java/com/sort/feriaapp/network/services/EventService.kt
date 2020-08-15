package com.sort.feriaapp.network.services

import com.sort.feriaapp.network.responses.EventResponse
import retrofit2.http.GET

interface EventService {

    @GET("/events")
    suspend fun fetchEvents(): EventResponse

}