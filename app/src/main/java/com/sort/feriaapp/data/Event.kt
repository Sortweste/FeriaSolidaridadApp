package com.sort.feriaapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event (
    @PrimaryKey val id: String,
    val title: String,
    @ColumnInfo(name = "image_url") val imageURL: String,
    val link: String?,
    val email: String?,
    val form: String?,
    val css: String?,
    val description: String,
    val InstitutionId: String
)