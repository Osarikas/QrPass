package ru.myitschool.work.utils

import android.content.Context
import android.content.res.ColorStateList
import android.widget.Button
import androidx.core.content.ContextCompat
import ru.myitschool.work.R

fun buttonRecolor(
    context: Context,
    btn: Button,
    bgColor: Int,
    textColor: Int
) {
    btn.backgroundTintList = ColorStateList.valueOf(
        ContextCompat.getColor(
            context,
            bgColor
        ))
    btn.setTextColor(context.getColor(textColor))
}