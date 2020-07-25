package com.sort.feriaapp.network.responses

data class ArticleResponse(
    val description: String,
    val events: List<EventResponse>,
    val homepage: String,
    val id: Int,
    val image_url: String,
    val institution: String,
    val meeting_url: String,
    val social_media: List<SocialMediaResponse>,
    val title: String,
    val type: String,
    val video_url: String
)