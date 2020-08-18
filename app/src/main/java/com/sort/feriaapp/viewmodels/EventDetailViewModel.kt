package com.sort.feriaapp.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.repositories.EventRepository

class EventDetailViewModel @ViewModelInject constructor(private val eventRepository: EventRepository, @Assisted savedStateHandle: SavedStateHandle): ViewModel() {

    private val eventId: String = savedStateHandle["eventId"]!!

    private val _getEventInfo: LiveData<Event> = eventRepository.getEventById(eventId).asLiveData()

    val eventInfo: LiveData<Event>
    get() = _getEventInfo


}