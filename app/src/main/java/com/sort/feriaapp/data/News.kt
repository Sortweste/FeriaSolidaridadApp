package com.sort.feriaapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    @ColumnInfo(name = "image_url") val imageURL: String?,
    @ColumnInfo(name = "video_url") val videoURL: String?,
    val link: String?,
    val enlace: String?
)