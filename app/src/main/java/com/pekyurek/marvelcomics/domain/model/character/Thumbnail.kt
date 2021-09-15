package com.pekyurek.marvelcomics.domain.model.character

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    @Json(name = "path")
    val path: String = "",
    @Json(name = "extension")
    val extension: String = ""
) : Parcelable {

    val url get() = "$path.$extension"
}