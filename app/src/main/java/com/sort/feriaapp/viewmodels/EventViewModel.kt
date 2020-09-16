package com.sort.feriaapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.data.repositories.EventRepository
import javax.inject.Inject

/*ViewModel for EventFragment*/
class EventViewModel @ViewModelInject constructor(private val eventRepository: EventRepository): ViewModel() {

    private val _getAllEvents = eventRepository.getAllEvents.asLiveData()

    val events: LiveData<List<EventMinimal>>
    get() = _getAllEvents

}