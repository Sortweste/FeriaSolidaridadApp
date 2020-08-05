package com.sort.feriaapp.data.repositories

import com.sort.feriaapp.data.dao.EventDao
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow

class EventRepository(private val eventDao: EventDao): SafeApiRequest() {

    val getAllEvents: Flow<List<EventMinimal>> = eventDao.getAllEventsMinimal()

    fun getEventById(id: Long) = eventDao.getEventById(id)

    companion object {
        @Volatile private var instance: EventRepository? = null
        fun getInstance(eventDao: EventDao) =
            instance ?: synchronized(this){
                instance ?: EventRepository(eventDao).also { instance = it }
            }
    }

}