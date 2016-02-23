package com.joebruckner.twocan.util

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View

fun View.snack(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

fun View.bigSnack(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun View.snack(
        text: String,
        action: String = "Action",
        color: Int = Color.WHITE,
        listener: (View) -> Unit
) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
            .setAction(action, listener)
            .setActionTextColor(color)
            .show()
}