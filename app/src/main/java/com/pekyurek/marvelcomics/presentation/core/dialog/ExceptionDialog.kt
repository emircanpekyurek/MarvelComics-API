package com.pekyurek.marvelcomics.presentation.core.dialog

import android.content.Context
import com.pekyurek.marvelcomics.domain.exception.base.BaseException
import com.pekyurek.marvelcomics.presentation.core.dialog.base.BaseAlertDialog

class ExceptionDialog(context: Context, exception: BaseException) : BaseAlertDialog(context) {

    init {
        setTitle(exception.titleResId)
        setMessage(exception.message)
        setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
        }
        create()
    }
}