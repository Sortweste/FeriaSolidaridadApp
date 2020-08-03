package com.sort.feriaapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sort.feriaapp.data.repositories.InstitutionRepository

class InstitutionDetailViewModelFactory(private val institutionRepository: InstitutionRepository, private val institutionId: Long): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InstitutionDetailViewModel(institutionRepository, institutionId) as T
    }
}