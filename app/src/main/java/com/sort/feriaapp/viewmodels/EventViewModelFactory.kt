package com.sort.feriaapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sort.feriaapp.data.repositories.EventRepository

class EventViewModelFactory(private val eventRepository: EventRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventViewModel::class.java))
            return EventViewModel(eventRepository) as T
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}