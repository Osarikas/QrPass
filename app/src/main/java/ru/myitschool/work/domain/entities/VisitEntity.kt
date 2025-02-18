package ru.myitschool.work.domain.entities

import java.util.Date

data class VisitEntity(
    val scanTime : Date,
    val readerId: Long,
    val type: String
)
