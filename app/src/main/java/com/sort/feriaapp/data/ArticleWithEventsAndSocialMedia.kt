package com.sort.feriaapp.data

import androidx.room.Embedded
import androidx.room.Relation


data class ArticleWithEventsAndSocialMedia(
    @Embedded val article: Article,
    @Relation(parentColumn = "id", entityColumn = "ArticleId")
    val events: List<Event>,
    @Relation(parentColumn = "id", entityColumn = "ArticleId")
    val socialMedia: List<SocialMedia>
)