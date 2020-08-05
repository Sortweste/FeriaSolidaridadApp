package com.sort.feriaapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.repositories.EventRepository

class EventDetailViewModel(private val eventRepository: EventRepository, private val eventId: Long): ViewModel() {

    private val _getEventInfo: LiveData<Event> = eventRepository.getEventById(eventId).asLiveData()

    val eventInfo: LiveData<Event>
    get() = _getEventInfo


}