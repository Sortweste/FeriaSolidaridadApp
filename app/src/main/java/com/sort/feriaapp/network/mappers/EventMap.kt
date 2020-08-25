package com.sort.feriaapp.network.mappers

import com.sort.feriaapp.data.Event
import com.sort.feriaapp.network.responses.EventResponse

fun EventResponse.toEvent() = Event(
    id = _id.`$oid`,
    title = title,
    description = description,
    link = link,
    email = "",
    form = "",
    css = "",
    imageURL = image_url,
    InstitutionId = ""
)