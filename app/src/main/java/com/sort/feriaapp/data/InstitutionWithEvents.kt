package com.sort.feriaapp.data

import androidx.room.Embedded
import androidx.room.Relation

data class InstitutionWithEvents(
    @Embedded val institution: Institution,
    @Relation(parentColumn = "id", entityColumn = "InstitutionId")
    val events: List<Event>
)