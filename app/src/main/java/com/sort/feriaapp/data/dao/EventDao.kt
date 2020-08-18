package com.sort.feriaapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.minimals.EventMinimal
import kotlinx.coroutines.flow.Flow

@Dao
abstract class EventDao: BaseDao<Event> {

    @Query("SELECT * FROM events")
    abstract fun getAllEvents(): Flow<List<Event>>

    @Query("SELECT id, image_url as imageURL FROM events")
    abstract fun getAllEventsMinimal(): Flow<List<EventMinimal>>

    @Query("SELECT * FROM events WHERE id=:id")
    abstract fun getEventById(id: String): Flow<Event>

}