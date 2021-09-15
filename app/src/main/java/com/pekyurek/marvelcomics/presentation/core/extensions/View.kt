package com.pekyurek.marvelcomics.presentation.core.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> ViewGroup.inflateDataBinding(
    @LayoutRes layoutResId: Int,
    attachToParent: Boolean = false,
): T {
    return DataBindingUtil.inflate(LayoutInflater.from(context),
        layoutResId,
        this,
        attachToParent)

}