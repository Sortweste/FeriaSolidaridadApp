package com.sort.feriaapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sort.feriaapp.data.InstitutionWithEvents
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.data.repositories.InstitutionRepository
import kotlinx.coroutines.launch

class InstitutionDetailViewModel(private val institutionRepository: InstitutionRepository, private val institutionId: Long): ViewModel() {

    private val _getInstitutionInfo: LiveData<InstitutionWithEvents> = institutionRepository.getInstitutionInfoById(institutionId).asLiveData()

    val institutionInfo: LiveData<InstitutionWithEvents>
    get() = _getInstitutionInfo

    val events: List<EventMinimal> = emptyList()


}