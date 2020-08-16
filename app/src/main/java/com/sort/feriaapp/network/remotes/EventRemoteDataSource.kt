package com.sort.feriaapp.network.remotes

import com.sort.feriaapp.network.services.EventService
import com.sort.feriaapp.utils.SafeApiRequest
import javax.inject.Inject

class EventRemoteDataSource @Inject constructor(private val eventService: EventService): SafeApiRequest() {

    suspend fun fetchEvents() = apiRequest { eventService.fetchEvents() }

}