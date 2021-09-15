package com.pekyurek.marvelcomics.presentation.core.extensions

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("loadCircleImageFromUrl")
fun loadCircleImageFromUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).circleCrop().into(imageView)
}

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("isVisible")
fun isVisible(view: View, visible: Boolean) {
    view.isVisible = visible
}

@BindingAdapter("setHasFixedSize")
fun setHasFixedSize(recyclerView: RecyclerView, hasFixedSize: Boolean) {
    recyclerView.setHasFixedSize(hasFixedSize)
}