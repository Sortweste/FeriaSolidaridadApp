package com.sort.feriaapp.interfaces

import android.view.View

interface RecyclerViewClickListener<T> {
    fun onCardViewClick(view: View, obj: T)
}