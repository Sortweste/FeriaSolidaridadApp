package com.sort.feriaapp.storage

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun putData(key: String, data: String) = sharedPreferences.edit().putString(key, data).apply()
    fun getData(key: String) = sharedPreferences.getString(key, null)

}