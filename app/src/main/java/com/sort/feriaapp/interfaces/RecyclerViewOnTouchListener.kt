package com.sort.feriaapp.interfaces

import android.view.MotionEvent
import android.view.View

/*Provide interface for OnLongPress in all elements of RecyclerView*/
interface RecyclerViewOnTouchListener<T> {

    //fun onImageTouch(flag: Boolean, obj: T, motionEvent: MotionEvent)
    fun onImageTouch(v: View, obj: T)

}