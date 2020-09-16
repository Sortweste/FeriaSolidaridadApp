package com.sort.feriaapp.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/*Singleton AlertDialog */
object AlertDialog {
    fun display(context: Context, title: String, message: String, positiveText: String) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { dialog, which ->
                
                // Respond to positive button press
            }
            .show()
    }
}