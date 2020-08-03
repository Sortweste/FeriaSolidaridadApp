package com.sort.feriaapp.data.repositories

import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.data.InstitutionWithEvents
import com.sort.feriaapp.data.dao.InstitutionDao
import com.sort.feriaapp.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow

class InstitutionRepository(private val institutionDao: InstitutionDao): SafeApiRequest() {

    val getAllInstitutions: Flow<List<Institution>> = institutionDao.getAllInstitutions()

    val institutionsWithEvents: Flow<List<InstitutionWithEvents>> = institutionDao.getAllInstitutionsInfo()

    fun getInstitutionInfoById(id: Long): InstitutionWithEvents{
        return institutionDao.getInstitutionInfoById(id)
    }

    suspend fun insertInstitution(institution: Institution){
        institutionDao.insert(institution)
    }

    suspend fun insertInstitutions(institutions: List<Institution>){
        institutionDao.insertMany(*institutions.toTypedArray())
    }


    companion object {
        @Volatile private var instance: InstitutionRepository? = null
        fun getInstance(institutionDao: InstitutionDao) =
            instance ?: synchronized(this){
                instance ?: InstitutionRepository(institutionDao).also { instance = it }
            }
    }

}