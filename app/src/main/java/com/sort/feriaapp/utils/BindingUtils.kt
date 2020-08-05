package com.sort.feriaapp.utils

import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sort.feriaapp.helpers.BindAdapter

@BindingAdapter("data")
@Suppress("UNCHECKED_CAST")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: List<T>?){
    if (recyclerView.adapter is BindAdapter<*>) {
        (recyclerView.adapter as BindAdapter<T>).setData(data)
    }
}

@BindingAdapter("setImageUrl")
fun bindImageUrl(view: ImageView, url: String?){
    if(!url.isNullOrBlank())
        Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, notVisible: Boolean){
    view.visibility = if(notVisible) View.GONE else View.VISIBLE
}

@BindingAdapter("facebookMessage")
fun setFacebookString(view: TextView, account: String?){
    if(!account.isNullOrBlank()){
        val textMessage = "Encuentranos en $account"
        view.text = textMessage
    }
}

@BindingAdapter("videoURL")
fun setWebViewValue(view: WebView, videoURL: String?){
    if(!videoURL.isNullOrBlank()){
        val url: String = "<iframe width=\"1583\" height=\"658\" src=\"https://www.youtube.com/embed/$videoURL\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe> "
        view.loadData(url, "text/html", null)
    }

}

@BindingAdapter("assistMessage")
fun setAssistString(view: TextView, link: String?){
    if(!link.isNullOrBlank()){
        val textMessage = "Asiste en $link"
        view.text = textMessage
    }
}