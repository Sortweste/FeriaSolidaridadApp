package com.sort.feriaapp.network.services

import com.sort.feriaapp.network.responses.EventResponse
import retrofit2.Response
import retrofit2.http.GET

/*Event Service to define all API operations*/
interface EventService {

    @GET("/events")
    suspend fun fetchEvents(): Response<List<EventResponse>>

}