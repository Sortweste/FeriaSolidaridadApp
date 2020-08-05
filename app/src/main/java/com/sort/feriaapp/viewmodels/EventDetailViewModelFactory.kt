package com.sort.feriaapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sort.feriaapp.data.repositories.EventRepository

class EventDetailViewModelFactory(private val eventRepository: EventRepository, private val eventId: Long): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventDetailViewModel(eventRepository, eventId) as T
    }

}