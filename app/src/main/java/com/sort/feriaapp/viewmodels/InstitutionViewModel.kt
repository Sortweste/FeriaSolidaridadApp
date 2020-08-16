package com.sort.feriaapp.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.data.repositories.InstitutionRepository
import kotlinx.coroutines.*

class InstitutionViewModel @ViewModelInject constructor(private val institutionRepository:InstitutionRepository): ViewModel() {

    private val _getAllInstitutions = institutionRepository
        .getAllInstitutions
        .asLiveData()

    val institutions: LiveData<List<Institution>>
    get() = _getAllInstitutions


    fun insert(institution: Institution) = viewModelScope.launch(Dispatchers.IO) {
        institutionRepository.insertInstitution(institution)
    }

}