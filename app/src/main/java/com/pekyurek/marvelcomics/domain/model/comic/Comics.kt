package com.pekyurek.marvelcomics.domain.model.comic


import android.annotation.SuppressLint
import com.squareup.moshi.Json
import java.text.SimpleDateFormat

data class Comics(
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @Json(name = "title")
    val title: String = "",
    @Json(name = "dates")
    val dates: List<Date>,
) {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    val digitalPurchaseDate = dates.firstOrNull { it.type == "digitalPurchaseDate" }?.date

    val digitalPurchaseDateMs = digitalPurchaseDate?.let { dateFormat.parse(it) }

}