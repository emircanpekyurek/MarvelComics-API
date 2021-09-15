package com.pekyurek.marvelcomics.domain.exception.service

import android.content.Context
import com.pekyurek.marvelcomics.R

class NullResponseException(context: Context) :
    ServiceException(
        context.getString(R.string.exception_service_null_response)
    )