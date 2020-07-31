package com.sort.feriaapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article (
    @PrimaryKey val id: Long,
    val description: String,
    val institution: String,
    val summary: String,
    @ColumnInfo(name = "image_url") val imageURL: String,
    @ColumnInfo(name = "logo_url") val logoURL: String,
    @ColumnInfo(name = "video_url") val videoURL: String?,
    @ColumnInfo(name = "meeting_url") val meetingURL: String?,
    val type: String?,
    val homepage: String?
)