package com.pekyurek.marvelcomics.domain.model.comic


import com.squareup.moshi.Json

data class Date(
    @Json(name = "date")
    val date: String = "",
    @Json(name = "type")
    val type: String = ""
)