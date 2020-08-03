package com.sort.feriaapp.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.repositories.InstitutionRepository
import com.sort.feriaapp.viewmodels.InstitutionDetailViewModelFactory
import com.sort.feriaapp.viewmodels.InstitutionViewModelFactory

object InjectorUtils {

    private fun getInstitutionRepository(context: Context):InstitutionRepository{
        return InstitutionRepository.getInstance(AppDatabase.getInstance(context).institutionDao())
    }

    fun provideInstitutionViewModelFactory(fragment: Fragment): InstitutionViewModelFactory{
        return InstitutionViewModelFactory(getInstitutionRepository(fragment.requireContext()))
    }

    fun provideInstitutionDetailVieModelFactory(context: Context, institutionId: Long): InstitutionDetailViewModelFactory{
        return InstitutionDetailViewModelFactory(getInstitutionRepository(context), institutionId)
    }

}