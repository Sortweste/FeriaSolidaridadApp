package com.sort.feriaapp.network.responses

import com.sort.feriaapp.helpers.IdHelper

data class EventResponse(
    val _id: IdHelper,
    val description: String,
    val image_url: String,
    val link: String,
    val title: String
)