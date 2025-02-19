package ru.myitschool.work.domain.entities

import java.util.Date

data class EmployeeEntranceEntity(
    val scanTime : Date,
    val readerName: String,
    val type: String
)
