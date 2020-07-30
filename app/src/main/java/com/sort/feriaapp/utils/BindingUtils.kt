package com.sort.feriaapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sort.feriaapp.helpers.BindAdapter

@BindingAdapter("data")
@Suppress("UNCHECKED_CAST")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: List<T>){
    if (recyclerView.adapter is BindAdapter<*>) {
        (recyclerView.adapter as BindAdapter<T>).setData(data)
    }
}

@BindingAdapter("setImageUrl")
fun bindImageUrl(view: ImageView,url: String){
    if(url.isNotBlank())
        Glide.with(view.context).load(url).into(view)
}