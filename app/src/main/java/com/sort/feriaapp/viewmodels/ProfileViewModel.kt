package com.sort.feriaapp.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.repositories.EventRepository
import com.sort.feriaapp.data.repositories.UserRepository

class ProfileViewModel @ViewModelInject constructor(private val eventRepository: EventRepository, private val userRepository: UserRepository): ViewModel(){

    /*    private val _getAllInstitutions = institutionRepository
        .getAllInstitutions
        .asLiveData()

    val institutions: LiveData<List<Institution>>
    get() = _getAllInstitutions
    */

}