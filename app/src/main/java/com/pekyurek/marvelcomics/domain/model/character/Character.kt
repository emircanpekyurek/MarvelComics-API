package com.pekyurek.marvelcomics.domain.model.character


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class Character(
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
) : Parcelable {
    val imageTransitionId get() = "${id}_$name"
}
