package com.sort.feriaapp.interfaces

import android.view.View

/*Provides interface for handling ClickListener on every element for RecyclerView*/
interface RecyclerViewClickListener<T> {
    fun onCardViewClick(view: View, obj: T)
}