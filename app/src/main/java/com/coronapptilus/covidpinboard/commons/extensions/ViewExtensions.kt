package com.coronapptilus.covidpinboard.commons.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Hides the keyboard using its view Context.
 */
fun View.hideKeyboard() {
    (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.showSoftInput(rootView, InputMethodManager.SHOW_FORCED)
}