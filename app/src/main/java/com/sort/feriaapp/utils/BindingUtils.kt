package com.sort.feriaapp.utils

import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.View
import android.webkit.WebView
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sort.feriaapp.interfaces.BindAdapter


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
        Glide.with(view.context).load(Uri.parse(url)).into(view)
}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, notVisible: Boolean){
    view.visibility = if(notVisible) View.GONE else View.VISIBLE
}

@BindingAdapter("videoURL")
fun setWebViewValue(view: WebView, videoURL: String?){
    if(!videoURL.isNullOrBlank()){
        val url: String = "<iframe width=\"300\" height=\"300\" src=\"https://www.youtube.com/embed/$videoURL\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe> "
        view.loadData(url, "text/html", null)
    }

}

@BindingAdapter("twitterText")
fun setTwitterAccountName(view: TextView, account:String?){
    if(!account.isNullOrBlank()){
        val name: String = "@${account}"
        view.text = name
        view.contentDescription = account
    }
}

@BindingAdapter("facebookText")
fun setFacebookAccountName(view: TextView, account:String?){
    if(!account.isNullOrBlank()){
        val name: String = "Visitanos en nuestra página de Facebook"
        view.text = name
        view.contentDescription = account
    }
}

@BindingAdapter("instagramText")
fun setInstagramAccountName(view: TextView, account:String?){
    if(!account.isNullOrBlank()){
        val name: String = "¡Siguenos en Instagram!"
        view.text = name
        view.contentDescription = account
    }
}

@BindingAdapter("sitioWebText")
fun setSitioWebAccountName(view: TextView, account:String?){
    if(!account.isNullOrBlank()){
        val name: String = "Visitanos en nuestro sitio web"
        view.text = name
        view.contentDescription = account
    }
}


@SuppressWarnings("deprecation")
@BindingAdapter("htmlFormattedText")
fun setHTMLFormText(view: TextView, text:String?){
    if(!text.isNullOrBlank()){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }else
            view.text = Html.fromHtml(text)
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
fun setSpinnerSelectedValue(spinner: Spinner, selectedValue: String?, newTextAttrChanged: InverseBindingListener){
    spinner.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) = newTextAttrChanged.onChange()
    }
    val pos = (spinner.adapter as ArrayAdapter<String?>).getPosition(selectedValue)
    spinner.setSelection(pos, true)
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun getSpinnerSelectedValue(spinner: Spinner): String = spinner.selectedItem.toString()
