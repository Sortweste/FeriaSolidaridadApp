package com.sort.feriaapp.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.repositories.InstitutionRepository
import com.sort.feriaapp.viewmodels.InstitutionViewModelFactory

object InjectorUtils {

    private fun getArticleRepository(context: Context):InstitutionRepository{
        return InstitutionRepository.getInstance(AppDatabase.getInstance(context).articleDao())
    }

    fun provideArticleViewModelFactory(fragment: Fragment): InstitutionViewModelFactory{
        return InstitutionViewModelFactory(getArticleRepository(fragment.requireContext()))
    }

}