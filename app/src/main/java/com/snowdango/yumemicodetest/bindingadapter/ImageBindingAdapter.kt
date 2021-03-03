package com.snowdango.yumemicodetest.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageBindingAdapter{
    @JvmStatic
    @BindingAdapter("imageURL")
    fun ImageView.loadImage(url: String?){
        Picasso.get().load(url).resize(192,192).into(this)
    }
}