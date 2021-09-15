package com.pekyurek.marvelcomics.domain.exception.service

import android.content.Context
import com.pekyurek.marvelcomics.R

class FailResponseException(context: Context, responseStatus: Int, message: String? = null) :
    ServiceException(
        if (message != null){
            "$message statusCode = $responseStatus"
        } else{
            context.getString(
                R.string.exception_service_fail_response_with_status,
                responseStatus
            )
        }

    )
