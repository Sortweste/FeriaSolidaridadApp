package com.sort.feriaapp.viewmodels

import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.InstitutionWithEvents
import com.sort.feriaapp.data.repositories.InstitutionRepository

class InstitutionDetailViewModel(private val institutionRepository: InstitutionRepository, private val institutionId: Long): ViewModel() {

    val institutionInfo: InstitutionWithEvents = institutionRepository.getInstitutionInfoById(institutionId)

}