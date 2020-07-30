package com.sort.feriaapp.helpers

interface BindAdapter<T> {
    fun setData(items: List<T>)
}