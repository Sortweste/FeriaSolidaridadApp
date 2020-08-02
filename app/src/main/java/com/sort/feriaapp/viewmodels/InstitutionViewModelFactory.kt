package com.sort.feriaapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sort.feriaapp.data.repositories.InstitutionRepository

class InstitutionViewModelFactory(private val institutionRepository: InstitutionRepository): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InstitutionViewModel::class.java))
            return InstitutionViewModel(institutionRepository) as T
        throw IllegalArgumentException("Unable to construct ViewModel")
    }

}