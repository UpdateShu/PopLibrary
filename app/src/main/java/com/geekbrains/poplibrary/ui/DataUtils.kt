package com.geekbrains.poplibrary.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar

// Показать SnackBar без кнопки действия
fun View.showSnackBarNoAction(
    title: String,
    lenght: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, title, lenght)
        .show()
}