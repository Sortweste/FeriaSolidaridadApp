package com.sort.feriaapp.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.repositories.EventRepository
import com.sort.feriaapp.data.repositories.InstitutionRepository
import com.sort.feriaapp.data.repositories.UserRepository
import com.sort.feriaapp.viewmodels.*

object InjectorUtils {

    private fun getInstitutionRepository(context: Context):InstitutionRepository{
        return InstitutionRepository.getInstance(AppDatabase.getInstance(context).institutionDao())
    }

    private fun getEventRepository(context: Context): EventRepository{
        return EventRepository.getInstance(AppDatabase.getInstance(context).eventDao())
    }

    private fun getUserRepository(context: Context): UserRepository{
        return UserRepository.getInstance(AppDatabase.getInstance(context).userDao())
    }

    fun provideInstitutionViewModelFactory(fragment: Fragment): InstitutionViewModelFactory{
        return InstitutionViewModelFactory(getInstitutionRepository(fragment.requireContext()))
    }

    fun provideInstitutionDetailVieModelFactory(context: Context, institutionId: Long): InstitutionDetailViewModelFactory{
        return InstitutionDetailViewModelFactory(getInstitutionRepository(context), institutionId)
    }

    fun provideEventViewModelFactory(fragment: Fragment): EventViewModelFactory{
        return EventViewModelFactory(getEventRepository(fragment.requireContext()))
    }

    fun provideEventDetailViewModelFactory(context: Context, eventId: Long): EventDetailViewModelFactory{
        return EventDetailViewModelFactory(getEventRepository(context), eventId)
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory{
        return LoginViewModelFactory(getUserRepository(context))
    }

}