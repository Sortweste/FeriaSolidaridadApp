package com.sort.feriaapp.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.sort.feriaapp.data.AppDatabase
import com.sort.feriaapp.data.repositories.EventRepository
import com.sort.feriaapp.data.repositories.InstitutionRepository
import com.sort.feriaapp.data.repositories.UserRepository
import com.sort.feriaapp.viewmodels.*

object InjectorUtils {

    fun provideInstitutionDetailVieModelFactory(context: Context, institutionId: Long): InstitutionDetailViewModelFactory{
        return InstitutionDetailViewModelFactory(getInstitutionRepository(context), institutionId)
    }


    fun provideEventDetailViewModelFactory(context: Context, eventId: Long): EventDetailViewModelFactory{
        return EventDetailViewModelFactory(getEventRepository(context), eventId)
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory{
        return LoginViewModelFactory(getUserRepository(context))
    }

}