package com.sort.feriaapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event (
    @PrimaryKey val id: Long,
    val title: String,
    @ColumnInfo(name = "image_url") val imageURL: String,
    val link: String,
    val description: String,
    val InstitutionId: Long
)