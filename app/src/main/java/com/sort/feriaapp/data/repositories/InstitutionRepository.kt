package com.sort.feriaapp.data.repositories

import androidx.lifecycle.LiveData
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.data.InstitutionWithEvents
import com.sort.feriaapp.data.dao.InstitutionDao
import com.sort.feriaapp.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InstitutionRepository @Inject constructor (private val institutionDao: InstitutionDao) {

    val getAllInstitutions: Flow<List<Institution>> = institutionDao.getAllInstitutions()

    val institutionsWithEvents: Flow<List<InstitutionWithEvents>> = institutionDao.getAllInstitutionsInfo()

    fun getInstitutionInfoById(id: String) = institutionDao.getInstitutionInfoById(id)


    suspend fun insertInstitution(institution: Institution){
        institutionDao.insert(institution)
    }

    suspend fun insertInstitutions(institutions: List<Institution>){
        institutionDao.insertMany(*institutions.toTypedArray())
    }

}