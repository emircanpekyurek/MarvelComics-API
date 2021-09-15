package com.pekyurek.marvelcomics.data

import com.pekyurek.marvelcomics.presentation.core.extensions.toMd5

object ApiKeyConstants {
    const val QUERY_NAME_API_KEY = "apikey"
    const val QUERY_NAME_HASH = "hash"
    const val QUERY_NAME_TIMESTAMP = "ts"

    const val PUBLIC_API_KEY = ""
    const val PRIVATE_API_KEY = ""

    fun getHashedKey(ts: Long) = "${ts}${PRIVATE_API_KEY}${PUBLIC_API_KEY}".toMd5()
}