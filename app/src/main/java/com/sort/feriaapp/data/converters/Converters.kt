package com.sort.feriaapp.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromList(list: List<String>) = Gson().toJson(list).toString()

    @TypeConverter
    @JvmStatic
    fun toList(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, listType)
    }

}