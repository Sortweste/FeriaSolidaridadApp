package com.sort.feriaapp.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.sort.feriaapp.data.InstitutionWithEvents
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.data.repositories.InstitutionRepository
import kotlinx.coroutines.launch

class InstitutionDetailViewModel @ViewModelInject constructor(private val institutionRepository: InstitutionRepository, @Assisted savedStateHandle: SavedStateHandle): ViewModel() {

    private val institutionId: String = savedStateHandle["institutionId"]!!

    private val _getInstitutionInfo: LiveData<InstitutionWithEvents> = institutionRepository.getInstitutionInfoById(institutionId).asLiveData()

    val institutionInfo: LiveData<InstitutionWithEvents>
    get() = _getInstitutionInfo

    val events: List<EventMinimal> = emptyList()


}