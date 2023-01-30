package com.example.movielister.util

import android.content.res.Resources
import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

