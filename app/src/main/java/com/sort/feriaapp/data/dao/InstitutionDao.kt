package com.sort.feriaapp.data.dao

import androidx.room.*
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.data.InstitutionWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
abstract class InstitutionDao: BaseDao<Institution> {

    @Transaction
    @Query("SELECT * FROM institutions")
    abstract fun getAllInstitutionsInfo(): Flow<List<InstitutionWithEvents>>

    @Query("SELECT * FROM institutions")
    abstract fun getAllInstitutions(): Flow<List<Institution>>

    @Transaction
    @Query("SELECT * FROM institutions WHERE id=:id")
    abstract suspend fun getInstitutionInfoById(id: Long): InstitutionWithEvents

}