package com.pekyurek.marvelcomics.data.repository

import com.pekyurek.marvelcomics.data.ApiKeyConstants
import com.pekyurek.marvelcomics.data.ApiKeyConstants.QUERY_NAME_API_KEY
import com.pekyurek.marvelcomics.data.ApiKeyConstants.QUERY_NAME_HASH
import com.pekyurek.marvelcomics.data.ApiKeyConstants.QUERY_NAME_TIMESTAMP
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalHttpUrl = original.url

        val ts = System.currentTimeMillis()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(QUERY_NAME_HASH, ApiKeyConstants.getHashedKey(ts))
            .addQueryParameter(QUERY_NAME_API_KEY, ApiKeyConstants.PUBLIC_API_KEY)
            .addQueryParameter(QUERY_NAME_TIMESTAMP, ts.toString())
            .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
