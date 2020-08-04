package com.sort.feriaapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "institutions")
data class Institution (
    @PrimaryKey val id: Long,
    val description: String?,
    val name: String,
    val summary: String,
    @ColumnInfo(name = "image_url") val imageURL: String,
    @ColumnInfo(name = "logo_url") val logoURL: String,
    @ColumnInfo(name = "video_url") val videoURL: String?,
    @ColumnInfo(name = "meeting_url") val meetingURL: String?,
    val type: String?,
    val meetup: String?,
    val website: String?,
    val twitter: String?,
    val instagram: String?,
    val facebook: String?
)