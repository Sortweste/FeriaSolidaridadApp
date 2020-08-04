package com.sort.feriaapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.repositories.EventRepository

class EventViewModel(private val eventRepository: EventRepository): ViewModel() {

    private val _getAllEvents = eventRepository.getAllEvents.asLiveData()

    val events: LiveData<List<Event>>
    get() = _getAllEvents

}