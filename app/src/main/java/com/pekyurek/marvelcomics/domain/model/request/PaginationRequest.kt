package com.pekyurek.marvelcomics.domain.model.request

import com.squareup.moshi.Json

open class PaginationRequest(
    @Json(name = "limit")
    var pageSize: Int = 0,
    @Json(name = "offset")
    var pageNumber: Int = 0
)