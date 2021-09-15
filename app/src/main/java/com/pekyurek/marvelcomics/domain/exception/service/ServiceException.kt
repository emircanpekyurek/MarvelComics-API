package com.pekyurek.marvelcomics.domain.exception.service

import com.pekyurek.marvelcomics.R
import com.pekyurek.marvelcomics.domain.exception.base.BaseException


open class ServiceException(message: String) : BaseException(message) {

    override val titleResId: Int = R.string.exception_title_service_error
}