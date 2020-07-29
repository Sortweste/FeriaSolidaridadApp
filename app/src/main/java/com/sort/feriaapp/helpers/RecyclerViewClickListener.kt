package com.sort.feriaapp.helpers

import android.view.View

interface RecyclerViewClickListener<T> {
    fun onCardViewClick(view: View, obj: T)
}