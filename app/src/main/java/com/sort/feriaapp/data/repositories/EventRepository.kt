package com.sort.feriaapp.data.repositories

import com.sort.feriaapp.data.dao.EventDao
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventDao: EventDao) {

    val getAllEvents: Flow<List<EventMinimal>> = eventDao.getAllEventsMinimal()

    fun getEventById(id: String) = eventDao.getEventById(id)

}