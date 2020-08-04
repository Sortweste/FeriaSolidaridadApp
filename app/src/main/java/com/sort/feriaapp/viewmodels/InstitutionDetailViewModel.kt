package com.sort.feriaapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sort.feriaapp.data.InstitutionWithEvents
import com.sort.feriaapp.data.repositories.InstitutionRepository
import kotlinx.coroutines.launch

class InstitutionDetailViewModel(private val institutionRepository: InstitutionRepository, private val institutionId: Long): ViewModel() {

    val institutionInfo = institutionRepository.getInstitutionInfoById(institutionId).asLiveData()

}