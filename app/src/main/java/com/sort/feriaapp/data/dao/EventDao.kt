package com.sort.feriaapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sort.feriaapp.data.Event
import kotlinx.coroutines.flow.Flow

@Dao
abstract class EventDao: BaseDao<Event> {

    @Query("SELECT * FROM events")
    abstract fun getAllEvents(): Flow<List<Event>>

}