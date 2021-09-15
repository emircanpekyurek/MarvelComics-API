package com.pekyurek.marvelcomics.domain.model.base

import com.squareup.moshi.Json

data class ListData<T>(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "limit")
    val limit: Int = 0,
    @Json(name = "offset")
    val offset: Int = 0,
    @Json(name = "total")
    val total: Int = 0,
    @Json(name = "results")
    val results: List<T> = listOf()
)