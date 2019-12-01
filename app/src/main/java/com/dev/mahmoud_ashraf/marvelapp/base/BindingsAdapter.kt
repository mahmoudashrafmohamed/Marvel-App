package com.dev.mahmoud_ashraf.marvelapp.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */

@BindingAdapter("srcUrl")
fun setImageUrl(view: ImageView, url: String?) {
    if (url?.isNullOrEmpty()==false)
        Glide.
            with(view.context)
            .load(url)
            .into(view)
}
