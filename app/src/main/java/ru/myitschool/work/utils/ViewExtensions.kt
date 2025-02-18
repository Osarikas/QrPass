package ru.myitschool.work.utils

import android.view.View

fun View.visibleOrGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}