package ru.myitschool.work.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateConverter(date: Date?) : String {
    if (date != null) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        println(dateFormat.format(date).toString())
        return dateFormat.format(date).toString()

    }
    return ""
}