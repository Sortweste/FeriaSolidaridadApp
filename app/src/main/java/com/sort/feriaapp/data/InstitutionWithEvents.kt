package com.sort.feriaapp.data

import androidx.room.Embedded
import androidx.room.Relation
import com.sort.feriaapp.data.minimals.EventMinimal

data class InstitutionWithEvents(
    @Embedded val institution: Institution,
    @Relation(parentColumn = "id", entityColumn = "InstitutionId")
    val events: List<Event>
)