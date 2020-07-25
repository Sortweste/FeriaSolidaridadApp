package com.sort.feriaapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "social_media")
data class SocialMedia (
    @PrimaryKey val id: Long,
    val name: String,
    val url: String,
    val type: String,
    val ArticleId: Long
)