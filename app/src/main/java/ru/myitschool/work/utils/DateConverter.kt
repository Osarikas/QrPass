package ru.myitschool.work.utils

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun dateTimeConverter(date: Date?) : String {
    if (date != null) {
        val dateFormat = SimpleDateFormat("HH:mm • yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date).toString()
    }
    return ""
}
fun monthConverter(date: Date?) : String {
    if (date != null) {
        val russianLocale = Locale("ru", "RU", "variant")
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", russianLocale)
        return localDate.format(formatter)
    }
    return ""
}
fun timeConverter(date: Date?) : String {
    if (date != null) {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(date).toString()
    }
    return ""
}
