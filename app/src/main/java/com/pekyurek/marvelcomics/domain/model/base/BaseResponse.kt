package com.pekyurek.marvelcomics.domain.model.base

import com.squareup.moshi.Json

abstract class BaseResponse<T> {
    @Json(name = "code")
    var code: Int? = null

    @Json(name = "status")
    var status: String = ""

    @Json(name = "copyright")
    var copyright: String = ""

    @Json(name = "attributionText")
    var attributionText: String = ""

    @Json(name = "attributionHTML")
    var attributionHTML: String = ""

    @Json(name = "etag")
    var etag: String = ""

    @Json(name = "data")
    var responseData: T? = null
}